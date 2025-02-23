using UnityEngine;

public class WorldManager : MonoBehaviour{
    public GameObject player;
    public static WorldManager Instance {get; private set;}

    void Awake(){
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
        ChunkManager.Instance.GenerateInitial();
    }

    void Update()
    {
        
    }
}
