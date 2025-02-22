import { useState } from "react";
import { View, Text, TextInput, Alert, ImageBackground, Dimensions, TouchableOpacity, StyleSheet } from "react-native";
import { Ionicons } from "@expo/vector-icons"; 

export default function LoginScreen({ navigation }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = () => {
    Alert.alert("Login Successful", "Welcome back!");
   
    navigation.navigate("Task")
  };

  return (
    <ImageBackground source={require("../../assets/aa.jpg")} style={styles.background} resizeMode="cover">
      <Text style={styles.appTitle}>CO₂Clean</Text>
      <View style={styles.overlay}>
        <Text style={styles.title}>Login</Text> 
        <View style={styles.inputContainer}>
          <Ionicons name="mail-outline" size={24} color="#D4AF37" style={styles.icon} />
          <TextInput
            style={styles.input}
            placeholder="Email"
            placeholderTextColor="#E5C100"
            keyboardType="email-address"
            value={email}
            onChangeText={setEmail}
          />
        </View>
        <View style={styles.inputContainer}>
          <Ionicons name="lock-closed-outline" size={24} color="#D4AF37" style={styles.icon} />
          <TextInput
            style={styles.input}
            placeholder="Password"
            placeholderTextColor="#E5C100"
            secureTextEntry
            value={password}
            onChangeText={setPassword}
          />
        </View>
        <TouchableOpacity style={styles.button} onPress={handleLogin}>
          <Text style={styles.buttonText}>Enter</Text> 
        </TouchableOpacity>
      </View>
    </ImageBackground>
  );
}

const { width, height } = Dimensions.get("window");
const styles = StyleSheet.create({
  background: {
    width: width,
    height: height,
    justifyContent: "center",
    alignItems: "center",
  },
  appTitle: {
    position: "absolute", 
    top: 100, 
    fontSize: 42,
    fontFamily: "serif", 
    fontWeight: "bold",
    color: "#D4AF37", 
    textAlign: "center",
    width: "100%", 
    textShadowColor: "rgba(0, 0, 0, 0.5)",
    textShadowOffset: { width: 2, height: 2 },
    textShadowRadius: 5,
  },
  overlay: {
    marginTop: "20%",
    backgroundColor: "rgba(0, 0, 0, 0.7)",
    padding: 25,
    borderRadius: 25,
    alignItems: "center",
    width: "83%",
  },
  title: {
    fontSize: 34,
    fontFamily: "serif", 
    fontWeight: "bold",
    color: "#E5C100",
    marginBottom: 30,
    textShadowColor: "rgba(0, 0, 0, 0.3)",
    textShadowOffset: { width: 1, height: 1 },
    textShadowRadius: 3,
  },
  inputContainer: {
    flexDirection: "row",
    alignItems: "center",
    marginBottom: 15,
    width: "100%",
    backgroundColor: "rgba(255, 255, 255, 0.1)",
    borderRadius: 8,
    padding: 10,
  },
  icon: {
    marginRight: 10,
  },
  input: {
    flex: 1,
    color: "#fff",
    fontSize: 16,
  },
  button: {
    backgroundColor: "#D4AF37", 
    padding: 15,
    borderRadius: 8,
    width: "100%",
    alignItems: "center",
    marginTop: 20,
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.3,
    shadowRadius: 4,
  },
  buttonText: {
    fontSize: 20,
    fontWeight: "bold",
    color: "#000",
  },
});
