package pl.silverpoint.exception;

public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -43758070115254802L;
	private int userId;
	
	public UserNotFoundException(int userId) {
		super();
		this.userId = userId;
	}

	public UserNotFoundException() {
	}

	public int getUserId() {
		return userId;
	}
}
