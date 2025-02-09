import React from "react";
import {
  StyleSheet,
  FlatList,
  Dimensions,
  View,
  Pressable,
  ActivityIndicator,
} from "react-native";
import JobCard from "../../components/JobCard";
import { useState } from "react";
import { Colors } from "@/constants/Colors";
import Ionicons from "@expo/vector-icons/Ionicons";
import AsyncStorage from "@react-native-async-storage/async-storage";
import axios from "axios";
import { useSession } from "@/hooks/context";

export default function ListingsScreen() {
  const [loading, setLoading] = useState<boolean>(false);
  const { session } = useSession();
  const [jobs, setJobs] = useState<any[]>([]);

  const getJobListings = async () => {
    setJobs([]);
    try {
      setLoading(true);
      const response = await axios.post(
        "http://192.168.20.5:8080/jobs/listings",
        {},
        { headers: { Authorization: `Bearer ${session}` } }
      );
      setLoading(false);
      console.log(response.data);
      setJobs((prevJobs) => [...prevJobs, ...response.data]);
    } catch (e) {
      console.log("Error occurred when fetching job listings");
    }
  };

  return (
    <View style={styles.container}>
      {loading ? (
        <ActivityIndicator style={styles.loading} />
      ) : (
        <Pressable style={styles.button} onPress={getJobListings}>
          <Ionicons name="reload" size={32} color="black" />
        </Pressable>
      )}

      <FlatList
        style={styles.listContent}
        data={jobs}
        renderItem={({ item }) => (
          <View>
            <JobCard item={item} />
          </View>
        )}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  titleContainer: {
    flexDirection: "column",
    alignItems: "center",
  },
  listContent: {
    padding: 8,
    width: Dimensions.get("window").width,
    flexGrow: 1,
  },
  button: {
    position: "absolute",
    padding: 16,
    shadowOpacity: 0.3,
    shadowRadius: 2,
    borderRadius: 50,
    elevation: 3,
    backgroundColor: Colors.light.text,
    shadowOffset: { width: 1, height: 1 },
    bottom: 100,
    zIndex: 1,
  },
  buttonText: {
    fontWeight: "bold",
  },
  loading: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    transform: [{ scale: 1.5 }],
  },
});
