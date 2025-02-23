using UnityEngine;
using UnityEngine.Tilemaps;

public class Player : MonoBehaviour
{
    public float speed = 5.0f;
    public ChunkManager chunkManager; // Reference to ChunkManager
    public RuleTile waterRuleTile; // Assign your water Rule Tile

    private Rigidbody2D rb;
    private Vector2 targetPosition;
    private bool isMoving = false;

    void Start()
    {
        rb = GetComponent<Rigidbody2D>();
        targetPosition = rb.position;
    }

    void Update()
    {
        if (Input.GetMouseButtonDown(0))
        {
            Vector2 newPosition = Camera.main.ScreenToWorldPoint(Input.mousePosition);
            SetTargetPosition(newPosition);
        }
        else if (Input.touchCount > 0)
        {
            Touch touch = Input.GetTouch(0);
            if (touch.phase == TouchPhase.Began)
            {
                Vector2 newPosition = Camera.main.ScreenToWorldPoint(touch.position);
                SetTargetPosition(newPosition);
            }
        }
    }

    void FixedUpdate()
    {
        if (isMoving)
        {
            Vector2 direction = (targetPosition - rb.position).normalized;
            Vector2 nextPosition = rb.position + direction * speed * Time.fixedDeltaTime;

            if (IsWaterTile(nextPosition))
            {
                isMoving = false;
                rb.linearVelocity = Vector2.zero;
                return;
            }

            rb.linearVelocity = direction * speed;

            if (Vector2.Distance(rb.position, targetPosition) < 0.1f)
            {
                rb.linearVelocity = Vector2.zero;
                isMoving = false;
            }
        }
        else
        {
            rb.linearVelocity = Vector2.zero;
        }
    }

    void SetTargetPosition(Vector2 newPosition)
    {
        if (!IsWaterTile(newPosition))
        {
            targetPosition = newPosition;
            isMoving = true;
        }
    }

    bool IsWaterTile(Vector2 position)
    {
        if (chunkManager == null || waterRuleTile == null) return false;

        GameObject chunk = chunkManager.GetChunkAtPosition(position);
        if (chunk == null) return false;

        Tilemap tilemap = chunk.GetComponentInChildren<Tilemap>();
        if (tilemap == null) return false;

        Vector3Int tilePosition = tilemap.WorldToCell(position);
        TileBase tile = tilemap.GetTile(tilePosition);

        return tile == waterRuleTile;
    }
}
