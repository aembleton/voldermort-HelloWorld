import voldemort.client.ClientConfig;
import voldemort.client.SocketStoreClientFactory;
import voldemort.client.StoreClient;
import voldemort.client.StoreClientFactory;
import voldemort.server.VoldemortConfig;
import voldemort.server.VoldemortServer;


public class voldermort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		VoldemortConfig config = VoldemortConfig.loadFromVoldemortHome("C:\\Users\\aembleton\\workspace\\orient\\voldermort");
		VoldemortServer server = new VoldemortServer(config);
		
		server.start();
		
		String bootstrapUrl = "tcp://localhost:6666";
		 StoreClientFactory factory = new SocketStoreClientFactory(new ClientConfig().setBootstrapUrls(bootstrapUrl));
		 
		 // create a client that executes operations on a single store
		 StoreClient client = factory.getStoreClient("test");
		 
		 if (client.get("greeting")==null) {
			 System.out.println("Could not find greeting.  Adding now.");
			 client.put("greeting", "hello world");
		 }else {
			 System.out.println(client.getValue("greeting")); 
		 }
		
		server.stop();
	}

}
