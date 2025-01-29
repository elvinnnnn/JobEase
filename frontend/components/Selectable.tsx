import { Pressable, Text, StyleSheet } from "react-native";
import { useState } from "react";

export default function Selectable({ text }: { text: string }) {
  const [selected, setSelected] = useState(false);
  return (
    <Pressable
      style={selected ? styles.selected : styles.unselected}
      onPress={() => setSelected(!selected)}
    >
      <Text style={styles.text}>{text}</Text>
    </Pressable>
  );
}

const styles = StyleSheet.create({
  selected: {
    backgroundColor: "white",
    padding: 5,
    borderRadius: 5,
    opacity: 1,
    marginRight: 6,
  },
  unselected: {
    backgroundColor: "white",
    padding: 5,
    borderRadius: 5,
    opacity: 0.5,
    marginRight: 6,
  },
  text: {
    fontSize: 16,
  },
});
