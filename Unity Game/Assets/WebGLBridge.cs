using UnityEngine;
using System.Runtime.InteropServices;

public class WebGLBridge : MonoBehaviour
{
    void Start()
    {
        Application.ExternalEval(@"
            window.addEventListener('message', function(event) {
                if (typeof event.data === 'string') {
                    SendMessage('WebGLBridge', 'OnMessageReceived', event.data);
                }
            }, false);
        ");
    }

    public void OnMessageReceived(string message)
    {
        Debug.Log("Message from React Native: " + message);

        LoginData loginData = JsonUtility.FromJson<LoginData>(message);
        if (loginData.type == "LOGIN")
        {
            string apiUrl = ConfigLoader.GetWorldApi() + loginData.userId;
            User.Instance.username = loginData.userId;
            WorldLoader worldLoader = FindObjectOfType<WorldLoader>();
            if (worldLoader != null)
            {
                worldLoader.LoadWorldFromAPI(apiUrl);
            }
        }
    }
}

[System.Serializable]
public class LoginData
{
    public string type;
    public string userId;
}
