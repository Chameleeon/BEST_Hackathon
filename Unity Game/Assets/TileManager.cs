using UnityEngine;
using UnityEngine.Tilemaps;

public class TileManager : MonoBehaviour


{
public static TileManager Instance {get; private set;}

public Tile[] desertTiles;
public Tile[] desertGrassTiles;
public Tile[] desertPropTiles;
public Tile[] desertWaterTiles;
public Tile[] farmlandTiles;
public Tile[] groundTiles;
public Tile[] pathTiles;
public Tile[] propTiles;
public Tile[] waterTiles;
public Tile[] waterBeachTiles;

    public RuleTile desertRuleTile;
    public RuleTile grassRuleTile;
    public RuleTile waterRuleTile;

    private void Awake()
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

        desertTiles = LoadTiles<Tile>("Tiles/Desert");
        desertGrassTiles = LoadTiles<Tile>("Tiles/DesertGrass");
        desertPropTiles = LoadTiles<Tile>("Tiles/DesertProps");
        desertWaterTiles = LoadTiles<Tile>("Tiles/DesertWater");
        farmlandTiles = LoadTiles<Tile>("Tiles/Farmland");
        groundTiles = LoadTiles<Tile>("Tiles/Ground");
        pathTiles = LoadTiles<Tile>("Tiles/Path") ;
        propTiles = LoadTiles<Tile>("Tiles/ForestProps");
        waterTiles = LoadTiles<Tile>("Tiles/Water");
        waterBeachTiles = LoadTiles<Tile>("WaterBeach");


        desertRuleTile = LoadTile<RuleTile>("Tiles/Rule/DesertRuleTile");
        grassRuleTile = LoadTile<RuleTile>("Tiles/Rule/GrassRuleTile");
        waterRuleTile = LoadTile<RuleTile>("Tiles/Rule/DesertWater");
    }

    private T[] LoadTiles<T>(string path) where T : TileBase
    {
        return Resources.LoadAll<T>(path);
    }

    private T LoadTile<T>(string path) where T : TileBase
    {
        return Resources.Load<T>(path);
    }
   public TileBase GetTileByName(string tileName)
{
    if (tileName == "DesertTile") return desertRuleTile;
    if (tileName == "GrassTile") return grassRuleTile;
    if (tileName == "WaterTile") return waterRuleTile;

    foreach (TileBase tile in propTiles)
    {
        if (tile.name == tileName) return tile;
    }

    foreach (TileBase tile in desertPropTiles)
    {
        if (tile.name == tileName) return tile;
    }

    return null; // Tile not found
}
 
    }