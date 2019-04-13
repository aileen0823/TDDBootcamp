1.given 一个有空位的停车场，一辆有牌照的车
  when 停车
  then 停车成功，出票


2.given 一个有空位的停车场，一辆无牌照的车
  when 停车
  then 停车失败，不出票


3.given 一个没有空位的停车场，一辆有牌照的车
  when 停车
  then 停车失败，不出票

4.given 一个有空位的停车场，一辆有重复车牌的车
  when 停车
  then 停车失败，不出票

5.given 用户取车，正确的票
  when 取车
  then 取车成功
  
6.given 用户取车，错误的票
  when 取车
  then 取车失败

7.given 用户拿票成功取车之后，拿着之前的票
  when  取车
  then 取车失败
   
