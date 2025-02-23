using UnityEngine;

[System.Serializable]
public class PillarResponse : MonoBehaviour{
    public Entry[] entries;
}

[System.Serializable]
public class Entry{
    public int id;
    public string lastActivity;
    public int pillarTypeId;
    public int userId;
    public float condition;
}
