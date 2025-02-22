import React, { useState } from "react";
import { View, Text, StyleSheet, TouchableOpacity, Image, ImageBackground, Alert } from "react-native";
import { Ionicons } from "@expo/vector-icons";
import * as ImagePicker from "expo-image-picker";

export default function ReciklazaVerification({ navigation }) {
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

  const submitImage = () => {
    if (!receiptImage) {
      Alert.alert("Upozorenje", "Molimo vas da prvo uslikate!");
      return;
    }
    Alert.alert("Uspješno", "Slika je uspješno priložena!");
  };

  return (
    <ImageBackground source={require("../../assets/ion.jpg")} style={styles.background}>
      <View style={styles.container}>
        <TouchableOpacity style={styles.backButton} onPress={() => navigation.goBack()}>
          <Ionicons name="arrow-back" size={30} color="#fff" />
        </TouchableOpacity>

        <Text style={styles.title}>Reciklaza</Text>

        <View style={styles.imageContainer}>
          {receiptImage ? (
            <Image source={{ uri: receiptImage }} style={styles.image} />
          ) : (
            <Text style={styles.placeholderText}>Nema slike</Text>
          )}

          <TouchableOpacity style={styles.cameraIcon} onPress={takePhoto}>
            <Ionicons name="camera" size={30} color="#D4AF37" />
          </TouchableOpacity>
        </View>

        <TouchableOpacity style={styles.submitButton} onPress={submitImage}>
          <Text style={styles.buttonText}>Priloži sliku</Text>
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
    backgroundColor: "rgba(34, 34, 34, 0.85)",
    justifyContent: "center",
    alignItems: "center",
    marginVertical: 40,
    borderRadius: 10,
    position: "relative",
  },
  image: {
    width: "100%",
    height: "100%",
    borderRadius: 10,
  },
  placeholderText: {
    color: "#D4AF37",
  },
  cameraIcon: {
    position: "absolute",
    bottom: 10,
    right: 10,
    backgroundColor: "rgba(0, 0, 0, 0.6)",
    padding: 8,
    borderRadius: 20,
  },
  submitButton: {
    backgroundColor: "#D4AF37",
    paddingVertical: 15,
    paddingHorizontal: 30,
    borderRadius: 10,
    marginTop: 20,
  },
  buttonText: {
    color: "#000",
    fontSize: 16,
    fontWeight: "bold",
  },
});
