import { useContext, createContext, type PropsWithChildren } from "react";
import { useStorageState } from "./useStorageState";
import axios from "axios";
import { Notifier, Easing } from "react-native-notifier";

const AuthContext = createContext<{
  signIn: (email: String, password: String) => void;
  signUp: (email: String, password: String) => void;
  signOut: () => void;
  session?: string | null;
  isLoading: boolean;
}>({
  signIn: () => null,
  signUp: () => null,
  signOut: () => null,
  session: null,
  isLoading: false,
});

// This hook can be used to access the user info.
export function useSession() {
  const value = useContext(AuthContext);
  return value;
}

export default function SessionProvider({ children }: PropsWithChildren) {
  const [[isLoading, session], setSession] = useStorageState("session");

  return (
    <AuthContext.Provider
      value={{
        signIn: async (email: String, password: String) => {
          // Perform sign-in logic here
          try {
            const response = await axios.post(
              "http://192.168.20.5:8080/users/login",
              {
                email: email,
                password: password,
              },
              {
                headers: {
                  "Content-Type": "application/json",
                },
              }
            );
            setSession(response.data.token);
            console.log(response.data);
            Notifier.showNotification({
              title: "Logged in!",
              description: "Set your preferences and start applying for jobs.",
              duration: 4000,
              showAnimationDuration: 800,
              showEasing: Easing.bounce,
              onHidden: () => console.log("Hidden"),
              onPress: () => console.log("Press"),
              hideOnPress: false,
            });
          } catch (error) {
            console.log("Error occurred when logging in", error);
          }
        },
        signUp: async (email: String, password: String) => {
          // Perform sign-up logic here
          console.log(email, password);
          try {
            const response = await axios.post(
              "http://192.168.20.5:8080/users/register",
              {
                email: email,
                password: password,
              },
              {
                headers: {
                  "Content-Type": "application/json",
                },
              }
            );
            console.log(response.data);
            Notifier.showNotification({
              title: "Thank you for registering!",
              description: "Login to start browsing jobs.",
              duration: 4000,
              showAnimationDuration: 800,
              showEasing: Easing.bounce,
              onHidden: () => console.log("Hidden"),
              onPress: () => console.log("Press"),
              hideOnPress: false,
            });
          } catch (error) {
            console.log("Error occurred when registering", error);
          }
        },
        signOut: () => {
          setSession(null);
        },
        session,
        isLoading,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}
