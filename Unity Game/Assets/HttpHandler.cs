using UnityEngine;
using UnityEngine.Networking;
using System.Collections;

public class HttpHandler : MonoBehaviour
{
    public static HttpHandler Instance {get; private set;}
    
    private void Awake()
    {
        if (Instance == null)
        {
            Instance = this;
            DontDestroyOnLoad(gameObject); 

            // TODO REMOVE BEFORE PRODUCTION
            System.Net.ServicePointManager.ServerCertificateValidationCallback = delegate { return true; };

        }
        else
        {
            Destroy(gameObject);
        }
    }
public IEnumerator GetRequest(string uri) {
    Debug.Log("Sending request to URL: " + uri);

    using (UnityWebRequest request = UnityWebRequest.Get(uri)) {
        request.useHttpContinue = false;
        yield return request.SendWebRequest();

        if (request.result == UnityWebRequest.Result.Success) {
            Debug.Log("Response: " + request.downloadHandler.text);

            PillarResponse response = new PillarResponse();

            Entry[] entries = JsonHelper.FromJson<Entry>(request.downloadHandler.text);
            response.entries = entries;

            foreach (Entry entry in response.entries) {
                User.Instance.pillarStates.Add(entry.condition);
            }
        } else {
            Debug.LogError("Error: " + request.error);
        }
    }
}

public IEnumerator SendWorldDataToAPI(string apiUrl, string json)
{
    Debug.Log("URL: " + apiUrl);
    Debug.Log("Json world: " + json);
    using (UnityWebRequest request = new UnityWebRequest(apiUrl, "POST"))
    {
        byte[] jsonBytes = new System.Text.UTF8Encoding().GetBytes(json);
        request.uploadHandler = new UploadHandlerRaw(jsonBytes);
        request.downloadHandler = new DownloadHandlerBuffer();
        request.SetRequestHeader("Content-Type", "application/json");

        yield return request.SendWebRequest();

        if (request.result == UnityWebRequest.Result.Success)
        {
            Debug.Log("World data successfully sent to API!");
        }
        else
        {
            Debug.LogError("Error sending world data: " + request.error);
        }
    }
}

public static class JsonHelper {
    public static T[] FromJson<T>(string json) {
        string newJson = "{ \"array\": " + json + "}";
        Wrapper<T> wrapper = JsonUtility.FromJson<Wrapper<T>>(newJson);
        return wrapper.array;
    }

    [System.Serializable]
    private class Wrapper<T> {
        public T[] array;
    }
}


}
