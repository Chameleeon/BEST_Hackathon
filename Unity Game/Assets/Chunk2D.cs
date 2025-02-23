using UnityEngine;
using UnityEngine.Tilemaps;

public class Chunk2D : MonoBehaviour
{
    private readonly int chunkSize = 5;
    public Tilemap tilemap;
    private Tile[] tiles;

    public void Start(){
        Generate();

    }

    public void Generate()
    {
        for (int x = 0; x < chunkSize; x++)
        {
            for (int y = 0; y < chunkSize; y++)
            {
            }
        }
    }
}
