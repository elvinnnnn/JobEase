import { Pressable, Text, StyleSheet } from "react-native";
import { useState, useEffect } from "react";

export default function Selectable({
  text,
  onSelect,
  initial,
}: {
  text: string;
  onSelect: () => void;
  initial: boolean;
}) {
  const [selected, setSelected] = useState(initial);
  useEffect(() => {
    setSelected(initial);
  }, [initial]);
  return (
    <Pressable
      style={selected ? styles.selected : styles.unselected}
      onPress={() => {
        onSelect();
        setSelected(!selected);
      }}
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
