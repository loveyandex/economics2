function [f] = requstBtcTime()
global thistime

import matlab.net.*
import matlab.net.http.*
r = RequestMessage;
uri = URI('http://localhost:1212/cx');
resp = send(r,uri);
status = resp.StatusCode;
f=[];
if status=='OK'
    for i=1:length(resp.Body.Data)
     price=resp.Body.Data(i).open;
     unixTime=resp.Body.Data(i).unixTime;
    f=[f;unixTime,price];
    end
end
thistime=resp.Body.Data(end).date

end

