using UnityEngine;
using UnityEngine.Tilemaps;
using System.Linq;
using System.Collections.Generic;
using System;

[Serializable]
public class WorldData
{
    public List<ChunkData> chunks = new List<ChunkData>();

}

[Serializable]
public class ChunkData
{
    public int chunkX;
    public int chunkY;
    public List<TileData> tiles = new List<TileData>();
}

[Serializable]
public class TileData
{
    public int x, y;
    public string tileType;
    public string propType;
}

public class ChunkManager : MonoBehaviour
{
    public static ChunkManager Instance { get; private set; }

    private int autosaveTimer = 3600;

    public GameObject player;
    public int viewDistance = 10;
    public GameObject chunkPrefab;
    public int chunkSize = 10;
    public int gridSize = 2;
    private Vector3 lastPlayerPosition;
    public float waterBiomeProbability = 0.1f;
    public float desertBiomeProbability = 0.4f;
    public float grassBiomeProbability = 0.5f;
    public float desertPropProbability = 0.1f;
    public float forestPropProbability = 0.5f;
    public float farmProbability = 0.01f;
    private Dictionary<Vector2Int, GameObject> activeChunks = new Dictionary<Vector2Int, GameObject>();

    public int worldSizeInChunks = 64;

    void Awake()
    {
        if (Instance == null)
        {
            Instance = this;
            DontDestroyOnLoad(gameObject);
        }
        else
        {
            Destroy(gameObject);
        }
    }

    void Start()
    {
        lastPlayerPosition = Vector3.zero;
    }

    void Update()
    {
        UpdateChunks(new Vector3(player.transform.position.x, player.transform.position.y, 0));
        autosaveTimer++;
        if (autosaveTimer >= 3600)
        {
            autosaveTimer = 0;
            SaveWorldAndSendToAPI();
        }
    }

    public void GenerateInitial()
    {
        for (int x = 0; x < gridSize; x++)
        {
            for (int y = 0; y < gridSize; y++)
            {
                GenerateChunk(x, y);
            }
        }
        SaveWorldAndSendToAPI();
    }

    private bool IsWithinWorldBounds(Vector2Int chunkPos)
    {
        return chunkPos.x >= 0 && chunkPos.x < worldSizeInChunks &&
               chunkPos.y >= 0 && chunkPos.y < worldSizeInChunks;
    }

    private Vector2Int GetChunkPosition(Vector3 position)
    {
        int chunkX = Mathf.FloorToInt(position.x / chunkSize);
        int chunkY = Mathf.FloorToInt(position.y / chunkSize);
        return new Vector2Int(chunkX, chunkY);
    }

    public void GenerateChunk(int chunkX, int chunkY)
    {
        Vector3 chunkPosition = new Vector3(chunkX * chunkSize, chunkY * chunkSize, 0);
        GameObject chunkObject = Instantiate(chunkPrefab, chunkPosition, Quaternion.identity);
        activeChunks[new Vector2Int(chunkX, chunkY)] = chunkObject;
        GameObject propChunkObject = Instantiate(chunkPrefab, chunkPosition, Quaternion.identity);
        Tilemap propTilemap = propChunkObject.GetComponentInChildren<Tilemap>();

        Tilemap tilemap = chunkObject.GetComponentInChildren<Tilemap>();
        if (tilemap != null)
        {
            FillChunkWithTiles(tilemap, propTilemap, chunkX, chunkY);
        }
    }

    private void FillChunkWithTiles(Tilemap terrainTilemap, Tilemap propTilemap, int chunkX, int chunkY)
    {
        TileBase desertTile = TileManager.Instance.desertRuleTile;
        TileBase grassTile = TileManager.Instance.grassRuleTile;
        TileBase waterTile = TileManager.Instance.waterRuleTile;

        TileBase[] desertProps = TileManager.Instance.desertPropTiles;
        TileBase[] genericProps = TileManager.Instance.propTiles;

        for (int x = 0; x < chunkSize; x++)
        {
            for (int y = 0; y < chunkSize; y++)
            {
                Vector2Int worldPos = new Vector2Int(chunkX * chunkSize + x, chunkY * chunkSize + y);
                Vector3Int tilePos = new Vector3Int(x, y, 0);

                if (IsOnWorldEdge(worldPos))
                {
                    terrainTilemap.SetTile(tilePos, waterTile);
                }
                else
                {
                    float noiseValue = Mathf.PerlinNoise(worldPos.x * 0.1f, worldPos.y * 0.1f);

                    if (noiseValue < waterBiomeProbability)
                    {
                        terrainTilemap.SetTile(tilePos, waterTile);
                    }
                    else if (noiseValue < grassBiomeProbability)
                    {
                        terrainTilemap.SetTile(tilePos, grassTile);

                        if (UnityEngine.Random.value < forestPropProbability && genericProps.Length > 0)
                        {
                            TileBase propTile = genericProps[UnityEngine.Random.Range(0, genericProps.Length)];
                            propTilemap.SetTile(tilePos, propTile);
                        }
                    }
                    else
                    {
                        terrainTilemap.SetTile(tilePos, desertTile);

                        if (UnityEngine.Random.value < desertPropProbability && desertProps.Length > 0)
                        {
                            TileBase propTile = desertProps[UnityEngine.Random.Range(0, desertProps.Length)];
                            propTilemap.SetTile(tilePos, propTile);
                        }
                    }
                }
            }
        }
    }



    private void LoadChunksAroundPlayer(Vector2Int playerChunkPos)
    {
        for (int x = -viewDistance; x <= viewDistance; x++)
        {
            for (int y = -viewDistance; y <= viewDistance; y++)
            {
                Vector2Int chunkPos = new Vector2Int(playerChunkPos.x + x, playerChunkPos.y + y);
                if (!activeChunks.ContainsKey(chunkPos))
                {
                    GenerateChunk(chunkPos.x, chunkPos.y);
                }
            }
        }
    }
    private void UnloadFarChunks(Vector2Int playerChunkPos)
    {
        List<Vector2Int> chunksToRemove = new List<Vector2Int>();

        foreach (var chunk in activeChunks)
        {
            if (Mathf.Abs(chunk.Key.x - playerChunkPos.x) > viewDistance ||
                Mathf.Abs(chunk.Key.y - playerChunkPos.y) > viewDistance)
            {
                chunksToRemove.Add(chunk.Key);
            }
        }

        foreach (var chunkPos in chunksToRemove)
        {
            Destroy(activeChunks[chunkPos]);
            activeChunks.Remove(chunkPos);
        }
    }

    private void UpdateChunks(Vector3 playerPosition)
    {
        Vector2Int playerChunkPos = GetChunkPosition(playerPosition);
        LoadChunksAroundPlayer(playerChunkPos);
        UnloadFarChunks(playerChunkPos);
    }

    private bool IsOnWorldEdge(Vector2Int worldPos)
    {
        return worldPos.x < chunkSize || worldPos.x >= (worldSizeInChunks - 1) * chunkSize ||
               worldPos.y < chunkSize || worldPos.y >= (worldSizeInChunks - 1) * chunkSize;
    }

    public GameObject GetChunkAtPosition(Vector2 worldPosition)
    {
        int chunkX = Mathf.FloorToInt(worldPosition.x / chunkSize);
        int chunkY = Mathf.FloorToInt(worldPosition.y / chunkSize);
        Vector2Int chunkKey = new Vector2Int(chunkX, chunkY);

        if (activeChunks.TryGetValue(chunkKey, out GameObject chunk))
        {
            return chunk;
        }

        return null;
    }
    public void SaveWorldAndSendToAPI()
    {
        WorldData worldData = new WorldData();

        foreach (var chunk in activeChunks)
        {
            Vector2Int chunkPos = chunk.Key;
            GameObject chunkObject = chunk.Value;

            ChunkData chunkData = new ChunkData
            {
                chunkX = chunkPos.x,
                chunkY = chunkPos.y
            };

            Tilemap terrainTilemap = chunkObject.GetComponentInChildren<Tilemap>();
            Tilemap propTilemap = chunkObject.GetComponentInChildren<Tilemap>();

            for (int x = 0; x < chunkSize; x++)
            {
                for (int y = 0; y < chunkSize; y++)
                {
                    Vector3Int tilePos = new Vector3Int(x, y, 0);

                    TileBase terrainTile = terrainTilemap.GetTile(tilePos);
                    TileBase propTile = propTilemap.GetTile(tilePos);

                    if (terrainTile != null)
                    {
                        TileData tileData = new TileData
                        {
                            x = x,
                            y = y,
                            tileType = terrainTile.name,
                            propType = propTile != null ? propTile.name : null
                        };

                        chunkData.tiles.Add(tileData);
                    }
                }
            }

            worldData.chunks.Add(chunkData);
        }

        string json = JsonUtility.ToJson(worldData, true);

        StartCoroutine(HttpHandler.Instance.SendWorldDataToAPI(ConfigLoader.GetWorldApi() + User.Instance.username, json));
    }
}

