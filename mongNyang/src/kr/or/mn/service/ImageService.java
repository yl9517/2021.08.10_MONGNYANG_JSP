package kr.or.mn.service;

public class ImageService {

	private static ImageService service = new ImageService();
	
	public static ImageService getService() {
		return service;
	}
	
	private ImageService() {};
}
