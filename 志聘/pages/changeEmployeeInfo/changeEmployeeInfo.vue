<!-- 还差的操作：业绩评分改成出勤率，和考核情况一起提前，加入新的按钮，时间问题，跳转 -->
<template>

	<view class="content3">
		<view class="uni-form-item">缺勤率
			<input class="uni-input" v-model="staffgrade">
		</view>
		<view class="uni-form-item">考核情况
			<input class="uni-input" v-model="staffcomment">
		</view>
		<button id="ensurechangeemployeeinfo" type="primary" style="margin-top: 20px;"
			@click="judgeEmployee()">保存评价</button>
		<view class="uni-form-item">
			<text>姓名</text><br />
			<text class="uni-input" style="background-color: #FFFFFF;" >{{name}}</text>
		</view>
		<view class="uni-form-item">
			<text>生日</text><br />
			<text class="uni-input"style="background-color: #FFFFFF;">{{staffbirthday}}</text>
		</view>
		<view class="uni-form-item">
			<text>性别</text><br />
			<text class="uni-input"style="background-color: #FFFFFF;">{{staffgender}}</text>
		</view>
		<view class="uni-form-item">
			<text>邮箱</text><br />
			<text class="uni-input"style="background-color: #FFFFFF;">{{staffemail}}</text>
		</view>
		<view class="uni-form-item">
			<text>身份证号</text><br/>
			<text class="uni-input"style="background-color: #FFFFFF;">{{staffidnumber}}</text>
		</view>
		<view class="uni-form-item">
			<text>账号</text><br/>
			<text class="uni-input"style="background-color: #FFFFFF;">{{staffacnumber}}</text>
		</view>
		<view class="uni-form-item">
			<text>工号</text><br/>
			<text class="uni-input"style="background-color: #FFFFFF;">{{jobNumber}}</text>
		</view>
		<view class="uni-form-item">
			<text>入职时间</text>
			<input class="uni-input" v-model="startTime">
		</view>
		<view class="uni-form-item">
			<text>是否离职</text><br/>
			<picker @change="bindPickerChange" :range="array">
			<label class="box1" >{{array[index]}}</label>
			</picker>
		</view>
		<view class="uni-form-item" v-show="flag" >
			<text>离职时间</text><br />
			<input class="uni-input"  v-model="endTime">
		</view>
		
		<view class="uni-form-item">职位
			<input class="uni-input" v-model="staffposition">
		</view>
		<view class="uni-form-item">部门
			<input class="uni-input" v-model="staffdepartment">
		</view>
		<button id="ensurechangeemployeeinfo" type="primary" style="margin-top: 20px;"
			@click="ensureChangeEmployeeInfo()">确认修改</button>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				name: undefined,
				staffdepartment: undefined,
				staffposition: undefined,
				staffgrade: undefined,
				staffcomment: undefined,
				staffbirthday: undefined,
				staffgender: undefined,
				staffemail: undefined,
				staffidnumber: undefined,
				staffacnumber: undefined,
				jobNumber: undefined,
				startTime: undefined,
				endTime: undefined,
				isEnd: undefined,
				array: ['是', '否'],
				index:0,
				companyId:undefined,
				
				index1:0,
				flag:true,


			};
		},
		methods: {
			judgeEmployee()
			{
				var that = this;
				uni.showModal({
					title: '提示',
					content: '您确定要如此评价该员工吗',
					success: function(res) {
						if (res.confirm) {
							uni.request({
								url: 'http://123.57.94.22:9091/updateAssessment.do',
								data: {
									accountNumber: that.staffacnumber,
									companyId:that.companyId,
									startTime: that.startTime,
									absenteeismRate:that.staffgrade,
									performance:that.staffcomment
								},
								header: {
									'content-type': 'application/x-www-form-urlencoded',
									'Accept': "application/json;charset=UTF-8"
								},
								method: 'POST',
								success: res => {
									console.log(res);
				                    if(res.data)
									{
										uni.showToast({
											title:'评价成功！',
											icon:'none',
											duration: 2000,
										})
										//location.reload();
									}
								}
							})
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				})
			},
			ensureChangeEmployeeInfo() {
				var that = this;
				uni.showModal({
					title: '提示',
					content: '您确定要修改该员工信息吗',
					success: function(res) {
						if (res.confirm) {
							uni.request({
								url: 'http://123.57.94.22:9091/updateExperience.do',
								data: {
									accountNumber: that.staffacnumber,
									companyId:that.companyId,
									departmentId : that.staffdepartment,
									positionId :that.staffposition,
									jobNumber: that.jobNumber,
									startTime: that.startTime,
									endTime: that.endTime,
									isEnd: that.isEnd,
								},
								header: {
									'content-type': 'application/x-www-form-urlencoded',
									'Accept': "application/json;charset=UTF-8"
								},
								method: 'POST',
								success: res => {
									console.log(res);
                                    if(res.data)
									{
										uni.showToast({
											title:'更新成功！',
											icon:'none',
											duration: 2000,
										})
										// location.reload();
									}
								}
							})
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				})

			},
			bindPickerChange: function(e) {
				this.index = e.target.value
				
				if (this.index == 0) {
					this.isEnd = true;
					this.flag = true;
					
				} else {
					this.isEnd = false;
					this.flag = false;
					
				}
			},
		}, //method
		
		
		onLoad: function(UserInfo) {
			var item = JSON.parse(UserInfo.item); // 字符串转对象
			this.companyId=uni.getStorageSync("companyId");
			
			for(var i=0;i<=item.experiences.length-1;i++){
				
				if(item.experiences.companyId==UserInfo.companyId && item.experiences.isEnd==false){
					 this.index1=i;
					break;
				}
			};
			console.log(this.index1);
			
			this.name = item.name;
			this.staffgender = item.gender;
			this.staffbirthday = item.birthday.toString().substr(0,10);
			this.staffemail = item.email;
			this.staffidnumber = item.identifyNumber;
			this.staffacnumber = item.accountNumber;
			this.jobNumber = item.experiences[this.index1].jobNumber;
			this.startTime = item.experiences[this.index1].startTime.toString().substr(0,10);
			if(item.experiences[this.index1].endTime!=null){
				this.endTime = item.experiences[this.index1].endTime.toString().substr(0,10);
			}
			this.isEnd = item.experiences[this.index1].isEnd;
			console.log(this.isEnd);
			this.staffcomment = item.experiences[this.index1].assessment.performance;
			this.staffdepartment = item.experiences[this.index1].departmentId;
			this.staffposition = item.experiences[this.index1].positionId;
			this.staffgrade = item.experiences[this.index1].assessment.absenteeismRate;
			if (this.isEnd == true) {
				{
					this.index = 0
				}
			} else {
				{
					this.index = 1
					this.flag = false;
				}
			}
			uni.request({
				url: 'http://123.57.94.22:9091/getDepartmentByCompanyId.do',
				data: {
					companyId: item.companyId,
				},
				header: {
					'content-type': 'application/x-www-form-urlencoded',
					'Accept': "application/json;charset=UTF-8"
				},
				method: 'POST',
				success: res => {
					console.log(res);
					for (i = 0; i++; i <= res.data.length - 1) {
						if (item.departmentId == res.data.experiences.departmentId) {
							this.staffdepartment = res.data.name
						}
					}
				}
			});
			uni.request({
				url: 'http://123.57.94.22:9091/getPositionByCompanyIdAndDepartmentId.do',
				data: {
					companyId: item.companyId,
					departmentId :item.experiences.departmentId
				},
				header: {
					'content-type': 'application/x-www-form-urlencoded',
					'Accept': "application/json;charset=UTF-8"
				},
				method: 'POST',
				success: res => {
					console.log(res);
					for (i = 0; i++; i <= res.data.length - 1) {
						if (item.positionId == res.data.positionId) {
							this.staffposition = res.data.name
						}
					}
				}
			})
			
		},


	}
</script>

<style>
	.content3 {
		margin: 25px;
		margin-top: 10px;
		font-size: 22px;
	}

	.box1 {
		background-color: #E2E2E2;

	}

	.uni-input {
		background-color: #F0F0F0;
		font-size: 25px;
		margin-top: 10px;
		font-weight: 400;
	}
	.uni-form-item{
		font-weight: 800;
		font-size: 20px;
	}
</style>
