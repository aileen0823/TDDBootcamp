两个停车场A/B 停车顺序 A-B
1.given A、B停车场都有车位，来了一辆车
  when 停车
  then 出票，停在A停车场
2.given A停车场满了，B停车场有车位，来了一辆车
  when 停车
  then 出票，停在B停车场
3.given A、B停车场均满了，来了一辆车
  when 停车
  then 不能出票，不能停
4.given A、B停车场均有车位，来了一辆车，该车车牌在A停车场已经存在
  when 停车
  then 不能出票，不能停
5.given A、B停车场均有车位，来了一辆车，该车车牌在B停车场已经存在
  when 停车
  then 不能出票，不能停
6.given 有A、B两个停车场，车停在B停车场，用户取车，正确的票
  when 取车
  then 取车成功
7.given 有A、B两个停车场，车停在A停车场，用户取车，正确的票
  when 取车
  then 取车成功