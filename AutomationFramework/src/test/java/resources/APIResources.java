package resources;

//Enum is special class in java which has collection of constants or methods
public enum APIResources {

	PostAPI("/posts"),
	AmendAPI("/posts/1"),	
	Comments("/posts/1/comments"),
	Albums("/albums");
	
	private String resource;

	APIResources(String resource) {
		this.resource = resource;
		}
	
	public String getResource() {
		return resource;
	}
}

