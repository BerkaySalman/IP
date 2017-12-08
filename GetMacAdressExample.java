package IP;

import java.net.*;
import java.util.Collections;

public class GetMacAdressExample {
	public static void main(String[] args) throws Exception{
		 
        for(NetworkInterface ni : Collections.list(NetworkInterface.getNetworkInterfaces())){
            System.out.printf("%s IP: %s MAC: %s%n",ni.getDisplayName(), extractPrimaryIPAddress(ni, Inet4Address.class), extractMacStringFrom(ni));
        }
    }
 
    static <AddressType extends InetAddress> AddressType extractPrimaryIPAddress(NetworkInterface ni, Class<AddressType> addressType) {
 
        for(InterfaceAddress ia : ni.getInterfaceAddresses()){
            if(addressType.equals(ia.getAddress().getClass())){
                return addressType.cast(ia.getAddress());
            }
        }
 
        return null; //UNKNOWN IP
    }
 
    private static String extractMacStringFrom(NetworkInterface ni) throws SocketException {
 
        byte[] hardwareAddress = ni.getHardwareAddress();
 
        if (hardwareAddress == null ){
            return "UNKNOWN MAC";
        }
 
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hardwareAddress.length; i++) {
            sb.append(String.format("%02X%S", hardwareAddress[i], i < hardwareAddress.length - 1 ? "-" : ""));
        }
 
        return sb.toString();
    }
}
