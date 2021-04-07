# cms-pms

第三版重构 cms-pms

controller 由三部分组成，分别是最高权限的admin，各个组织的leader和基础的user。

admin包含新增用户，查看任意用户，查看所有用户的分页，修改用户，删除用户

- [X] POST /user 新增用户

  新增用户前要用拦截器校验当前用户是否有admin权限，判断权限后，判断插入的新用户的属性是否满足（手机号，角色，工种，班组，组织），之后对手机号进行加密作为初始密码进行存储。

- [x] PUT /user 修改用户

  修改用户信息以及 用户角色 和 用户工种 ，但不能修改用户是否被删除 以及 修改id

- [ ] GET /user/{userId} 查看用户
- [ ] DELETE /user/ 删除用户 （->/user/{userId}）
- [ ] ~~POST /role/{userId} (-> PUT) 修改用户权限~~(被包含在修改用户中)
- [ ] ~~POST /profession/{userId} (-> PUT) 修改用户~~

leader包含了

- [ ] GET /infos 查看本机构或部门下的所有员工的信息列表 PageInfo
- [ ] PUT /infos post user info list 进行操作
- [ ] PUT /info/{userId} post user info userId 进行操作
- [ ] GET /info/{userId} get user info userId 进行操作
- [ ] GET /attendances/{date} 查看本机构或部门下的所有员工的考勤列表 PageInfo 以天来计算
- [ ] PUT /attendance/{date}/user/{userId} 修改某人的某天的考勤
- [ ] GET /schedules/{userId} 查看本机构或部门下的所有员工的排班
- [ ] POST /schedules 批量修改排班信息
- [ ] POST /schedules/{userId} 修改员工排班 请假的行为？ 由schedules_type 代表状态的转移 请假时 设置为 请假未批准 ， leader 可以修改为请假已批准

User 包含了

- [ ] POST /login 登录
- [ ] GET /attendance 查看考勤 考勤的结果 由 schedule 进行排定
- [ ] POST /attendance 新增考勤
- [ ] GET /info 查看信息
- [ ] POST /info 修改信息 （ -> PUT）
- [ ] GET /schedule 查看排班
- [ ] POST /schedule 请假等操作 实际上是对排班信息的一种修改

P:

- mybatis-plus 从service的getbyId 不能识别id 的类型 如果出现id类型错误 只有当执行时 才能够发现错误