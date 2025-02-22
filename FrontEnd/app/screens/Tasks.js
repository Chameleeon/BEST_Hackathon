import React, { useState } from "react";
import { View, Text, StyleSheet, TouchableOpacity, Image, ScrollView, ImageBackground } from "react-native";
import { Ionicons } from "@expo/vector-icons";

const tasks = [
  { id: 1, name: "Voznja biciklom do posla", category: "Transport", description: "Koristi bicikl ili gradski prevoz kako bi smanjio emisiju CO2.", image: require("../../assets/hermes.png") },
  { id: 2, name: "Energija", category: "Energija", description: "Smanji potrošnju električne energije u svom domaćinstvu.", image: require("../../assets/hefest.png") },
  { id: 3, name: "Baci svoj otpad u kante za reciklazu", category: "Otpad", description: "Recikliraj i smanji otpad kako bi zaštitio okolinu.", image: require("../../assets/hades.png") },
  { id: 4, name: "Kupi Banane", category: "Prehrana", description: "Smanji unos mesa i konzumiraj održive namirnice.", image: require("../../assets/demetra.jpeg") }
];

export default function TaskScreen({ navigation }) {
  const [selectedTask, setSelectedTask] = useState(null);
  const [filter, setFilter] = useState(null);

  const filteredTasks = filter ? tasks.filter(task => task.category === filter) : tasks;

  return (
    <ImageBackground source={require("../../assets/ion.jpg")} style={styles.background}>
      <View style={styles.container}>
        <Text style={styles.welcomeText}>Dobrodošli, Milan</Text>
        <View style={styles.filterWrapper}>
          <ScrollView horizontal showsHorizontalScrollIndicator={false} contentContainerStyle={styles.filterScroll}>
          <View style={styles.filterContainer}>
              {['Sve', 'Transport', 'Energija', 'Otpad', 'Prehrana'].map(category => (
                <TouchableOpacity 
                  key={category} 
                  style={[styles.filterButton, (filter === category || (filter === null && category === 'Sve')) && styles.activeFilter]} 
                  onPress={() => setFilter(category === 'Sve' ? null : category)}>
                  <Text style={styles.filterText}>{category}</Text>
                </TouchableOpacity>
              ))}
            </View>
          </ScrollView>
        </View>
        <View style={styles.taskList}>
          {filteredTasks.map((task) => (
            <TouchableOpacity key={task.id} style={[styles.taskCard, selectedTask?.id === task.id && styles.expandedCard]} onPress={() => setSelectedTask(selectedTask?.id === task.id ? null : task)}>
              <Text style={styles.taskName}>{task.name}</Text>
              <Image source={task.image} style={styles.taskImage} />
              {selectedTask?.id === task.id && (
                <View style={styles.taskDetails}>
                  <Text style={styles.taskDescription}>{task.description}</Text>
                  <View style={styles.buttonContainer}>
                    <TouchableOpacity 
                      style={styles.acceptButton} 
                      onPress={() => {
                        if (task.category === "Transport") {
                          navigation.navigate("TransportVerification", { description: task.description, transport: task.name });
                        } else if (task.category === "Prehrana") {
                          navigation.navigate("PrehranaVerification", { description: task.description, prehrana: task.name });
                        }
                        else if (task.category === "Otpad") {
                            navigation.navigate("ReciklazaVerification", { description: task.description, prehrana: task.name });
                        }
                        else if (task.category === "Energija") {
                            navigation.navigate("EnergijaVerification", { description: task.description, prehrana: task.name });
                        }
                        
                      }}
                    >
                      <Ionicons name="checkmark-circle" size={20} color="#fff" />
                      <Text style={styles.buttonText}>Prihvati</Text>
                    </TouchableOpacity>
                    <TouchableOpacity style={styles.cancelButton} onPress={() => setSelectedTask(null)}>
                      <Ionicons name="close-circle" size={20} color="#fff" />
                      <Text style={styles.buttonText}>Odbij</Text>
                    </TouchableOpacity>
                  </View>
                </View>
              )}
            </TouchableOpacity>
          ))}
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
    backgroundColor: "rgba(0, 0, 0, 0.7)",
    paddingTop: 50,
  },
  welcomeText: {
    fontSize: 22,
    color: "#fff",
    textAlign: "center",
    marginBottom: 10,
  },
  filterContainer: {
    flexDirection: "row",
    justifyContent: "space-around",
    paddingVertical: 10,
    marginLeft: 10
  },
  filterButton: {
    paddingVertical: 5,
    paddingHorizontal: 15,
    borderRadius: 20,
  },
  activeFilter: {
    backgroundColor: "#D4AF37",
  },
  filterText: {
    color: "#fff",
    fontSize: 16,
  },
  taskList: {
    padding: 10,
  },
  taskCard: {
    flexDirection: "row",
    alignItems: "center",
    backgroundColor: "#222",
    padding: 15,
    borderRadius: 10,
    marginVertical: 5,
  },
  expandedCard: {
    flexDirection: "column",
    padding: 20,
  },
  taskName: {
    flex: 1,
    fontSize: 18,
    color: "#fff",
  },
  taskImage: {
    width: 40,
    height: 40,
    borderRadius: 20,
  },
  taskDetails: {
    marginTop: 10,
  },
  taskDescription: {
    color: "#fff",
    fontSize: 14,
    marginBottom: 10,
  },
  buttonContainer: {
    flexDirection: "row",
    justifyContent: "space-between",
  },
  acceptButton: {
    flexDirection: "row",
    alignItems: "center",
    backgroundColor: "#4CAF50",
    padding: 10,
    borderRadius: 8,
    flex: 1,
    marginRight: 5,
  },
  cancelButton: {
    flexDirection: "row",
    alignItems: "center",
    backgroundColor: "#D32F2F",
    padding: 10,
    borderRadius: 8,
    flex: 1,
    marginLeft: 5,
  },
  buttonText: {
    color: "#fff",
    fontSize: 14,
    marginLeft: 5,
  },
});