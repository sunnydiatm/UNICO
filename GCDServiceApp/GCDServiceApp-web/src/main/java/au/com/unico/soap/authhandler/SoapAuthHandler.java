package au.com.unico.soap.authhandler;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.xml.namespace.QName;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import au.com.unico.dao.UserDao;
import au.com.unico.rest.GCDRestServiceImpl;
import au.com.unico.utils.AuthUtils;

/**
 * This class acts a an interceptor for SOAP requests to the server 
 * and provides the security services.
 * @author Sunny Singh
 */
public class SoapAuthHandler implements SOAPHandler<SOAPMessageContext> {

	private Logger logger = LogManager.getLogger(SoapAuthHandler.class);
	
	@Inject 
	UserDao userDao;
	
	/* (non-Javadoc)
	 * @see javax.xml.ws.handler.Handler#handleMessage(javax.xml.ws.handler.MessageContext)
	 */
	@Override
	public boolean handleMessage(SOAPMessageContext context) {

		logger.debug("Server : handleMessage()......");
		Boolean outgoingMessage = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (!outgoingMessage) {
			try {
				SOAPMessage soapMsg = context.getMessage();
				SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
				SOAPHeader soapHeader = soapEnv.getHeader();
	        	try {
					AuthUtils.authenticate(userDao, (Map<String, List<String>>)context.get(MessageContext.HTTP_REQUEST_HEADERS));
				} catch (Exception e) {
					e.printStackTrace();
					generateSOAPErrMessage(soapMsg, e.getMessage() + " From handler");
				}
				soapMsg.writeTo(System.out);
			} catch (SOAPException e) {
				System.err.println(e);
			} catch (IOException e) {
				System.err.println(e);
			}

		}
		return true;
	}

	/* (non-Javadoc)
	 * @see javax.xml.ws.handler.Handler#handleFault(javax.xml.ws.handler.MessageContext)
	 */
	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return true;
	}

	@Override
	public void close(MessageContext context) {
		logger.debug("Server : close()......");
	}

	@Override
	public Set<QName> getHeaders() {
		logger.debug("Server : getHeaders()......");
		return null;
	}

	private void generateSOAPErrMessage(SOAPMessage msg, String reason) {
		try {
			SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
			SOAPFault soapFault = soapBody.addFault();
			soapFault.setFaultString(reason);
			throw new SOAPFaultException(soapFault);
		} catch (SOAPException e) {
		}
	}

	
}