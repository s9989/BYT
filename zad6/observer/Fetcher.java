package observer;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import memento.Caretaker;


public class Fetcher {
	
	private URLConnection urlconnection = null;
	
	private Caretaker storageManager;
	
	public Fetcher() {
		this.storageManager = new Caretaker();
		this.clients = new Hashtable<String, ArrayList<Client>>(); 
		this.sites = new ArrayList<Site>();
	}
	
	private ArrayList<Site> sites;
	
	public void addSite(String url) {
		Site site = new Site(url);
		site.restoreFromMemento( storageManager.getMemento(url) );
		this.sites.add(site);
	}
	
	public Fetcher(URLConnection urlconnection) {
		this.urlconnection = urlconnection;
	}

	public void run() {
		
		System.out.println("Modified: " + urlconnection.getLastModified());
		
	}
	
	private Hashtable<String, ArrayList<Client>> clients;
	
	public void addClient(Client client) {
		
		System.out.println(client.url);
		
		if (!this.clients.containsKey(client.url)) {
			this.clients.put(client.url, new ArrayList<Client>());
			this.addSite(client.url);
		}
		ArrayList<Client> AL = this.clients.get(client.url);
		AL.add(client);
		this.clients.put(client.url, AL);
	}
	
	public void fetch() {
		
		System.out.println("Fetch lunched at " + (new Date()).toString());
		System.out.println("Number of sites to check: " + this.sites.size());

		for (Site site : this.sites) {
			
			System.out.print("Checking site: " + site.getUrl() + "..." );
			System.out.print(" [" + site.getModifiedDate() + "] ");
			
			try {
				
				URL url = new URL(site.getUrl());
				URLConnection connection = url.openConnection();
			
				if (site.hasChanged(connection)) {
					System.out.print(" changed! ");
					for (Client client : clients.get(site.getUrl())) {
						client.alert();
					}
				}
				
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
			
			System.out.println("");
		}
	}
	
}
