using System.Collections.Generic;
using UnityEngine;

public class User : MonoBehaviour
{
    public static User Instance {get; private set;}
    public string username = "pero123";
    public List<float> pillarStates = new List<float>();
    private int updateCounter = 3600;
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
    }

    private void Update(){
        updateCounter++;
        if(updateCounter >= 3600){
            updateCounter = 0;

            StartCoroutine(HttpHandler.Instance.GetRequest(ConfigLoader.GetApiUrl()));
            if(pillarStates.Count >= 1){
                CalculateBiomeProbabilities();
            }
        }
    }

void CalculateBiomeProbabilities() {
    float averagePillarValue = (pillarStates[0] + pillarStates[1] + pillarStates[2] + pillarStates[3]) / 4;

    ChunkManager.Instance.desertBiomeProbability = 1 - averagePillarValue; 
    ChunkManager.Instance.desertPropProbability = 1 - averagePillarValue; 

    ChunkManager.Instance.farmProbability = pillarStates[3]; 

    ChunkManager.Instance.waterBiomeProbability = averagePillarValue * 0.5f; 
    ChunkManager.Instance.grassBiomeProbability = averagePillarValue * 0.7f; 
    ChunkManager.Instance.forestPropProbability = averagePillarValue; 

    NormalizeBiomeProbabilities();
}

void NormalizeBiomeProbabilities() {
    float total = ChunkManager.Instance.waterBiomeProbability + 
                  ChunkManager.Instance.desertBiomeProbability + 
                  ChunkManager.Instance.grassBiomeProbability + 
                  ChunkManager.Instance.desertPropProbability + 
                  ChunkManager.Instance.forestPropProbability + 
                  ChunkManager.Instance.farmProbability;

    if (total > 1) {
        ChunkManager.Instance.waterBiomeProbability /= total;
        ChunkManager.Instance.desertBiomeProbability /= total;
        ChunkManager.Instance.grassBiomeProbability /= total;
        ChunkManager.Instance.desertPropProbability /= total;
        ChunkManager.Instance.forestPropProbability /= total;
        ChunkManager.Instance.farmProbability /= total;
    }
}

}
