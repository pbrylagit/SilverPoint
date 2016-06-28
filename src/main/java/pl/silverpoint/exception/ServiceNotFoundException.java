package pl.silverpoint.exception;

public class ServiceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -566849637978742966L;
	private long serviceId;
	
	public ServiceNotFoundException(long serviceId) {
		super();
		this.serviceId = serviceId;
	}

	public long getServiceId() {
		return serviceId;
	}
}
