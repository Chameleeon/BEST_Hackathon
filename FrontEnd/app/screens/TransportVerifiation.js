import React, { useState, useEffect } from "react";
import { View, Text, StyleSheet, TouchableOpacity, ImageBackground } from "react-native";
import MapView, { Marker, Polyline } from "react-native-maps";
import { Ionicons } from "@expo/vector-icons";
import * as Location from "expo-location";

export default function TransportVerifiationScreen({ navigation, route }) {
  const { description, transport } = route.params;
  const [location, setLocation] = useState(null);
  const [routeCoordinates, setRouteCoordinates] = useState([]);
  const [tracking, setTracking] = useState(false);
  const [timeElapsed, setTimeElapsed] = useState(0);
  const [speed, setSpeed] = useState(0);
  let locationSubscription = null;

  useEffect(() => {
    let interval;
    if (tracking) {
      interval = setInterval(() => setTimeElapsed(prev => prev + 1), 1000);
    } else {
      clearInterval(interval);
    }
    return () => clearInterval(interval);
  }, [tracking]);

  useEffect(() => {
    (async () => {
      let { status } = await Location.requestForegroundPermissionsAsync();
      if (status !== "granted") {
        alert("Lokacijska dozvola nije odobrena!");
        return;
      }
      let loc = await Location.getCurrentPositionAsync({});
      setLocation(loc.coords);
    })();
  }, []);

  const startTracking = async () => {
    setTracking(true);
    setRouteCoordinates([]);
    setTimeElapsed(0);
    locationSubscription = await Location.watchPositionAsync(
      { accuracy: Location.Accuracy.High, timeInterval: 1000, distanceInterval: 5 },
      (newLocation) => {
        setLocation(newLocation.coords);
        setRouteCoordinates(prev => [...prev, newLocation.coords]);
        setSpeed(newLocation.coords.speed * 3.6 || 0);
      }
    );
  };

  const stopTracking = () => {
    setTracking(false);
    if (locationSubscription) locationSubscription.remove();
  };

  return (
    <ImageBackground source={require("../../assets/ion.jpg")} style={styles.background}>
      <View style={styles.container}>
        <TouchableOpacity style={styles.backButton} onPress={() => navigation.goBack()}>
          <Ionicons name="arrow-back" size={30} color="#fff" />
        </TouchableOpacity>
        <Text style={styles.header}>{transport}</Text>
        <Text style={styles.description}>{description}</Text>
        <Text style={styles.timer}>{`${Math.floor(timeElapsed / 60)}:${(timeElapsed % 60).toString().padStart(2, "0")}`}</Text>
        <MapView 
          style={styles.map} 
          initialRegion={location ? { 
            latitude: location.latitude, 
            longitude: location.longitude, 
            latitudeDelta: 0.01, 
            longitudeDelta: 0.01 
          } : undefined}
        >
          {location && <Marker coordinate={location} />}
          <Polyline coordinates={routeCoordinates} strokeWidth={5} strokeColor="blue" />
        </MapView>
        <View style={styles.bottomContainer}>
          {!tracking ? (
            <TouchableOpacity style={styles.startButton} onPress={startTracking}>
              <Ionicons name="play" size={24} color="#fff" />
              <Text style={styles.buttonText}>Start</Text>
            </TouchableOpacity>
          ) : (
            <TouchableOpacity style={styles.finishButton} onPress={stopTracking}>
              <Ionicons name="stop" size={24} color="#fff" />
              <Text style={styles.buttonText}>Finish</Text>
            </TouchableOpacity>
          )}
          <View style={styles.speedContainer}>
            <Text style={styles.speed}>{speed.toFixed(1)}</Text>
            <Text style={styles.speedLabel}>km/h</Text>
          </View>
        </View>
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
  header: {
    fontSize: 22,
    fontWeight: "bold",
    color: "#fff",
    marginTop: 10,
    marginLeft : 30
  },
  backButton: {
    position: "absolute",
    top: 60,
    left: 20,
    zIndex: 10,
  },
  description: {
    fontSize: 18,
    color: "#ccc",
    textAlign: "center",
    marginVertical: 10,
  },
  timer: {
    fontSize: 28,
    color: "#4CAF50",
    fontWeight: "bold",
    marginVertical: 10,
  },
  map: {
    width: "90%",
    height: 250,
    borderRadius: 10,
    marginBottom: 10,
  },
  bottomContainer: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "space-between",
    width: "90%",
  },
  speedContainer: {
    width: 80,
    height: 80,
    borderRadius: 40,
    backgroundColor: "rgba(34,34,34,0.7)",
    justifyContent: "center",
    alignItems: "center",
    marginLeft: 10,
  },
  speed: {
    fontSize: 24,
    fontWeight: "bold",
    color: "#4CAF50",
  },
  speedLabel: {
    fontSize: 14,
    color: "#fff",
  },
  startButton: {
    flexDirection: "row",
    alignItems: "center",
    backgroundColor: "#4CAF50",
    padding: 15,
    borderRadius: 10,
    justifyContent: "center",
    flex: 1,
  },
  finishButton: {
    flexDirection: "row",
    alignItems: "center",
    backgroundColor: "#D32F2F",
    padding: 15,
    borderRadius: 10,
    justifyContent: "center",
    flex: 1,
  },
  buttonText: {
    color: "#fff",
    fontSize: 16,
    marginLeft: 5,
  },
});