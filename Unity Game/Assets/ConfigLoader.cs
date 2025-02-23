using System;
using UnityEngine;

[System.Serializable]
public class Config {
    public string pillarApiUrl;
    public string worldApi;
}

public class ConfigLoader : MonoBehaviour
{
    public static ConfigLoader Instance { get; private set; }
    private static Config config;
    private string configPath = "config";

    private void Awake()
    {
        if (Instance == null)
        {
            Instance = this;
            DontDestroyOnLoad(gameObject);
            LoadConfig();
        }
        else
        {
            Destroy(gameObject);
        }
    }

    private void LoadConfig()
    {
        TextAsset configFile = Resources.Load<TextAsset>(configPath);
        if (configFile != null)
        {
            config = JsonUtility.FromJson<Config>(configFile.text);
        }
        else
        {
            Debug.LogError("Config file not found! Make sure it's in Assets/Resources/");
        }
    }

    public static string GetApiUrl()
    {
        if (config == null)
        {
            return string.Empty;
        }
        return config.pillarApiUrl;
    }

    public static string GetWorldApi(){
        if(config == null){
            return string.Empty;
        }
        return config.worldApi;
    }
}
