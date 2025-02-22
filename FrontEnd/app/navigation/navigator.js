import { createStackNavigator } from "@react-navigation/stack";
import HomeScreen from "../screens/home";
import LoginScreen from "../screens/Login";
import TaskScreen from "../screens/Tasks";
import TransportVerification from "../screens/TransportVerifiation";
import PrehranaVerification from "../screens/PrehranaVerification";
import ReciklazaVerification from "../screens/ReciklazaVerification";
import EnergijaVerification from "../screens/EnergijaVerification";
import RegisterScreen from "../screens/Register";

const Stack = createStackNavigator();

export default function AppNavigator() {


  return (
    <Stack.Navigator screenOptions={{ headerShown: false }}>
       <Stack.Screen name="Login" component={LoginScreen} />
       <Stack.Screen name="Home" component={HomeScreen} />
       <Stack.Screen name="Task" component={TaskScreen} />
       <Stack.Screen name="TransportVerification" component={TransportVerification} />
       <Stack.Screen name="PrehranaVerification" component={PrehranaVerification} />
       <Stack.Screen name="ReciklazaVerification" component={ReciklazaVerification} />
       <Stack.Screen name="EnergijaVerification" component={EnergijaVerification} />
       <Stack.Screen name="RegisterScreen" component={RegisterScreen} />
       
    </Stack.Navigator>
  );
}
