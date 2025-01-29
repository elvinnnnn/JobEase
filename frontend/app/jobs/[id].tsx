import { View, Text } from "react-native";
import { useLocalSearchParams } from "expo-router";

export default function JobPage() {
  const { id } = useLocalSearchParams();
  return (
    <View>
      <Text>Job Page = {id}</Text>
    </View>
  );
}
