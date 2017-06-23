import org.apache.commons.io.IOUtils
import java.nio.charset.*

flowFile = session.get()
if (flowFile == null) {
    return
}
int valid = 0
def slurper = new groovy.json.JsonSlurper()
long lat
long lng
try {
	
flowFile = session.write(flowFile, {inputStream, outputStream ->
  text = IOUtils.toString(inputStream, StandardCharsets.UTF_8)
	if (text.contains('PositionLatitude') && text.contains('PositionLongitude'))
	{
		obj = slurper.parseText(text)
		lat = obj.PositionLatitude
		lng = obj.PositionLongitude
		if (lat > 0 && lng > 0)
		{
			valid = 1
		}
	}
  
  outputStream.write(text.getBytes(StandardCharsets.UTF_8))
} as StreamCallback)

} catch(e) {
  log.error('Something went wrong', e)
  session.transfer(flowFile, REL_FAILURE)
}

if(valid == 1){
	//log.info('success :  text ' + text)
	session.transfer(flowFile, REL_SUCCESS)
}
else{
	//log.info('failure :  lat ' + lat + ' lng ' + lng)
	session.transfer(flowFile, REL_FAILURE)
}
