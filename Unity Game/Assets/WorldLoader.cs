using UnityEngine;
using UnityEngine.Networking;
using System.Collections;
using UnityEngine.Tilemaps;

public class WorldLoader : MonoBehaviour
{
    public void LoadWorldFromAPI(string apiUrl)
    {
        StartCoroutine(FetchWorldData(apiUrl));
    }

    IEnumerator FetchWorldData(string apiUrl)
    {
        using (UnityWebRequest request = UnityWebRequest.Get(apiUrl))
        {
            yield return request.SendWebRequest();

            if (request.result == UnityWebRequest.Result.Success)
            {
                Debug.Log("World data received: " + request.downloadHandler.text);
                WorldData worldData = JsonUtility.FromJson<WorldData>(request.downloadHandler.text);
                ReconstructWorld(worldData);
            }
            else if (request.responseCode == 404)             {
                Debug.LogWarning("No world data found. Creating a new world...");
                GenerateNewWorld();
            }
            else
            {
                Debug.LogError("Error fetching world data: " + request.error);
            }
        }
    }

    void ReconstructWorld(WorldData worldData)
    {
        foreach (ChunkData chunkData in worldData.chunks)
        {
            Vector2Int chunkPos = new Vector2Int(chunkData.chunkX, chunkData.chunkY);
            ChunkManager.Instance.GenerateChunk(chunkPos.x, chunkPos.y);

            GameObject chunkObject = ChunkManager.Instance.GetChunkAtPosition(new Vector2(chunkData.chunkX * ChunkManager.Instance.chunkSize, chunkData.chunkY * ChunkManager.Instance.chunkSize));
            Tilemap terrainTilemap = chunkObject.GetComponentInChildren<Tilemap>();
            Tilemap propTilemap = chunkObject.GetComponentInChildren<Tilemap>();

            foreach (TileData tile in chunkData.tiles)
            {
                Vector3Int tilePos = new Vector3Int(tile.x, tile.y, 0);
                
                TileBase terrainTile = TileManager.Instance.GetTileByName(tile.tileType);
                if (terrainTile != null)
                {
                    terrainTilemap.SetTile(tilePos, terrainTile);
                }

                if (!string.IsNullOrEmpty(tile.propType))
                {
                    TileBase propTile = TileManager.Instance.GetTileByName(tile.propType);
                    if (propTile != null)
                    {
                        propTilemap.SetTile(tilePos, propTile);
                    }
                }
            }
        }
    }

    private void GenerateNewWorld()
    {
        ChunkManager.Instance.GenerateInitial();
        StartCoroutine(SaveNewWorldToAPI());
    }

    private IEnumerator SaveNewWorldToAPI()
    {
        string json = SerializeWorld();
        string saveUrl = ConfigLoader.GetWorldApi() + User.Instance.username; 
        using (UnityWebRequest request = new UnityWebRequest(saveUrl, "POST"))
        {
            byte[] bodyRaw = System.Text.Encoding.UTF8.GetBytes(json);
            request.uploadHandler = new UploadHandlerRaw(bodyRaw);
            request.downloadHandler = new DownloadHandlerBuffer();
            request.SetRequestHeader("Content-Type", "application/json");

            yield return request.SendWebRequest();

            if (request.result == UnityWebRequest.Result.Success)
            {
                Debug.Log("New world saved successfully!");
            }
            else
            {
                Debug.LogError("Failed to save new world: " + request.error);
            }
        }
    }

    private string SerializeWorld()
    {
        WorldData worldData = new WorldData();
        return JsonUtility.ToJson(worldData);
    }
}
