import React, { useState } from "react";
import { View, Text, StyleSheet, TouchableOpacity, Image, ImageBackground } from "react-native";
import { Ionicons } from "@expo/vector-icons";
import * as ImagePicker from "expo-image-picker";

export default function PrehranaVerification({ navigation }) {
  const [receiptImage, setReceiptImage] = useState(null);

  const takePhoto = async () => {
    const permission = await ImagePicker.requestCameraPermissionsAsync();
    if (permission.granted) {
      let result = await ImagePicker.launchCameraAsync({
        mediaTypes: ImagePicker.MediaTypeOptions.Images,
        allowsEditing: true,
        aspect: [4, 3],
        quality: 1,
      });

      if (!result.canceled) {
        setReceiptImage(result.assets[0].uri);
      }
    }
  };

  return (
     <ImageBackground source={require("../../assets/ion.jpg")} style={styles.background}>
    <View style={styles.container}>
      <TouchableOpacity style={styles.backButton} onPress={() => navigation.goBack()}>
        <Ionicons name="arrow-back" size={30} color="#fff" />
      </TouchableOpacity>

      <Text style={styles.title}>Priloži račun</Text>

      <View style={styles.imageContainer}>
        {receiptImage ? (
          <Image source={{ uri: receiptImage }} style={styles.image} />
        ) : (
          <Text style={styles.placeholderText}>Nema slike</Text>
        )}
      </View>

      <TouchableOpacity style={styles.button} onPress={takePhoto}>
        <Ionicons name="camera" size={24} color="#000" />
        <Text style={styles.buttonText}>Slikaj račun</Text>
      </TouchableOpacity>
    </View>
    </ImageBackground>
  );
}

const styles = StyleSheet.create({
    background: {
        flex: 1,
        resizeMode: "cover",
      },
  container: {
    flex: 1,

    alignItems: "center",
    paddingTop: 50,
  },
  backButton: {
    position: "absolute",
    top: 60,
    left: 20,
    zIndex: 10,
  },
  title: {
    fontSize: 24,
    color: "#D4AF37",
    fontWeight: "bold",
    marginTop: 10,
  },
  imageContainer: {
    width: 300,
    height: 400,
    backgroundColor: "rgba(34, 34, 34, 0.95)", 
    justifyContent: "center",
    alignItems: "center",
    marginVertical: 60,
    borderRadius: 10,
},
  image: {
    width: "100%",
    height: "100%",
    borderRadius: 10,
  },
  placeholderText: {
    color: "#D4AF37",
  },
  button: {
    flexDirection: "row",
    alignItems: "center",
    backgroundColor: "#D4AF37",
    padding: 15,
    borderRadius: 10,
  },
  buttonText: {
    color: "#000",
    fontSize: 16,
    marginLeft: 5,
  },
});
