
maxfaravani=[];
close all
last=btime(end,end)
clear pf kf

up=0
low=0

for k=1:1
   
c1=ALL(:,k);
c1 = c1(imag(c1) == 0);
q=zeros(1,length(c1));

for j=1:length(c1)
   if c1(j)>=last
       up=up+1
   else
       low=low+1;
   end
end

low/length(c1)
up/length(c1)



end




for k=1:10
   
% figure(k)
c1=ALL(:,k);
c1 = c1(imag(c1) == 0);
q=zeros(1,length(c1));

minc=min(c1);
maxc=max(c1);

if ~isreal(maxc) || ~isreal(minc) 
    continue
end

p=10;
number=(maxc-minc)/p
dol=linspace(minc,maxc,number);
farav=[];
for i=1:number
     farav(i)=0;
for j=1:length(c1)
x1=minc+p*i;
pf(i)=x1;
x0=minc+p*(i-1);
kf(i)=x0;

if c1(j)>=x0 && c1(j)<=x1
    farav(i)= farav(i)+1;
end 
end
end

d=farav./sum(farav);
csd=zeros(1,length(farav));
for i =1:length(farav)
    
for j=1:i
    csd(i)=csd(i)+d(j);
end
    
    
EV(k)=sum(d.*(pf))
end
% hold on
% plot(dol,farav./sum(farav),'--')
%  figure(k+100)
% plot(dol,csd)
end
%%
% plot(dol,csd)
plot(EV,'r--*')
