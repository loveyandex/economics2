import matlab.net.*
import matlab.net.http.*
r = RequestMessage;
uri = URI('http://localhost:1212/cx');
resp = send(r,uri)
status = resp.StatusCode