/*
 * Author : Shivakumar Soppannavar
 * Start Date: 11/19/2015
 * Last edited :  11/21/2015
 * Class program where constructors take 0,2, or 4 parameters to initialize 
 * the respective fields of socket components and returns the value in JSON format by overriding toString function.
 */
public class Output {

	private String protocol;
	private String localIpAddress;
	private String foriegnIpAddress;
	private String state;

	public Output (){};
	public Output (String protocol, String localIpAddress){
		this.protocol =  protocol;
		this.localIpAddress= localIpAddress;
	};
	public Output(String protocol, String localIpAddress, String foriegnIpAddress, String state){
		this.protocol =  protocol;
		this.localIpAddress =localIpAddress;
		this.foriegnIpAddress = foriegnIpAddress;
		this.state = state;
	}
	
	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getLocalIpAddress() {
		return localIpAddress;
	}

	public void setLocalIpAddress(String localIpAddress) {
		this.localIpAddress = localIpAddress;
	}

	public String getForiegnIpAddress() {
		return foriegnIpAddress;
	}

	public void setForiegnIpAddress(String foriegnIpAddress) {
		this.foriegnIpAddress = foriegnIpAddress;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
	//return "{ " + "Protocol Used = " + protocol + " Local Address is = " + localIpAddress + " Foreign Address is = "
				//+ foriegnIpAddress + " State is= " + state + " }";
		return  protocol +  localIpAddress + foriegnIpAddress + state ;
	}
}
