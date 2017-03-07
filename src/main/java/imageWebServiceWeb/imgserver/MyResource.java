package imageWebServiceWeb.imgserver;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    /**
     * Identifier RESPONSE_NO.
     */
    private static final int RESPONSE_NO = 200;

    /**
     * This API is provided to post a image or other file
     *  to a specific
     *
     * @param uploadedStream
     *            Select image or other file to upload.
     * @param fileDetail
     *            the file details
     * @param dirType
     *            Enter directory name
     *            (input/output/master/feature/train/model/rules) to post
     *            content.
     * @responseMessage 404 Bad Request
     * @responseMessage 401 Unauthorized
     * @responseMessage 400 DMLEException: There was an exception in persisting
     *                  the content.
     * @responseMessage 200 OK
     * @return success or an error response.
     * @throws Exception
     *             exception
     */
    @POST
    @Path("/post/object")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
            MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_OCTET_STREAM })
    public final Response postObjectV2Auth(
            @FormDataParam("file") final InputStream uploadedStream,
            @FormDataParam("file") final FormDataContentDisposition fileDetail) throws Exception {
        Object output = null;
        try {
        	
            byte[] bytes = IOUtils.toByteArray(uploadedStream);
            try {
            	File jpgFile = new File("F:/WebServiceTestFolder/OutPut.jpg");
                OutputStream out = new FileOutputStream(jpgFile);
                out.write(bytes);
                out.flush();
                out.close();
            } finally {
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.toString())
                    .build();
        }
        return Response.status(RESPONSE_NO).entity(output).build();
    }  
}
