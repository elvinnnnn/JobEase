import { useContext, createContext, type PropsWithChildren } from "react";
import { useStorageState } from "./useStorageState";
import axios from "axios";

const AuthContext = createContext<{
  signIn: (email: String, password: String) => void;
  signOut: () => void;
  session?: string | null;
  isLoading: boolean;
}>({
  signIn: () => null,
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
              "http://localhost:8080/register",
              {
                email,
                password,
              }
            );
            console.log(response.data);
          } catch {
            console.log("Error occurred when logging in");
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
