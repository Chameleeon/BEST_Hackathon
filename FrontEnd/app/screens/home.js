import React from "react";
import { View, Text, StyleSheet, TouchableOpacity } from "react-native";
import { WebView } from "react-native-webview";
import { Ionicons } from "@expo/vector-icons";

export default function HomeScreen({ navigation }) {
  return (
    <View style={styles.container}>
      <WebView
        source={{ uri: "http://172.20.10.2:8000" }}
        style={styles.webview}
        allowsFullscreenVideo
      />

      <View style={styles.navBar}>
        <TouchableOpacity onPress={() => navigation.navigate("Home")}> 
          <Ionicons name="home" size={24} color="#fff" />
        </TouchableOpacity>
        <Text style={styles.navTitle}>SRKLES</Text>
        <TouchableOpacity onPress={() => navigation.navigate("Task")}> 
          <Ionicons name="menu" size={24} color="#fff" />
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#000",
  },
  webview: {
    flex: 1,
  },
  navBar: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "space-between",
    paddingHorizontal: 15,
    height: 60,
    position: "absolute",
    bottom: 0,
    left: 0,
    right: 0,
    paddingBottom: 10,
  },
  navTitle: {
    fontSize: 20,
    color: "#fff",
    fontWeight: "bold",
  },
});
