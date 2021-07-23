<template>
	<view>
		<view>
		    <text style="font-size: 20px;margin: 20px;font-weight: 800;">新员工部门：</text>
		    <input class="uni-input" focus placeholder="请输入新员工部门号" style="font-size: 25px;margin: 20px;margin-top: 10px;" @input="setDepartmentId" v-model="departmentId":value="departmentId"/>
		</view>
		<view>
		    <text style="font-size: 20px;margin: 20px;font-weight: 800;">新员工职位：</text>
		    <input class="uni-input" focus placeholder="请输入员工职位号" style="font-size: 25px;  margin: 20px;margin-top: 10px;" @input="setPositionId" v-model="positionId":value="positionId"/>
		</view>
		<button type=primary style="font-size: 20px;margin-left: 130px;margin-right: 130px;" @click="hire()">确认录用</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				accountNumber:'',
				departmentId:'',
				positionId:''
			}
		},
		onLoad:function(e)
		{
			this.accountNumber=e.accountNumber
		},
		methods: {
			setDepartmentId(e)
			{
				this.departmentId=e.target.value
			},
			setPositionId(e)
			{
				this.positionId=e.target.value
			},
			hire()
			{
				if(this.positionId==''||this.departmentId=='')
				{
					uni.showToast({
						title:'输入内容不能为空！',
						icon:'none',
						duration: 2000
					})
				}
				else
				{
					uni.request({
						url:'http://123.57.94.22:9091/hireEmployee.do',
						complete: ()=> {},
						data:{
							accountNumber: this.accountNumber,
							departmentId: this.departmentId,
							positionId: this.positionId
						},
						header:{
							'content-type':'application/x-www-form-urlencoded',
							'Accept':"application/json;charset=UTF-8"
						},
						method:'POST',
						success:res=>{
								uni.showToast({
									title:'录用成功！',
									icon:'none',
									duration: 2000,
								}),
								uni.navigateTo({
									url:'../companyOperate/companyOperate'
								})
						}
					});
				}
			}
		}
	}
</script>

<style>
.uni-input{
	background-color: #F0F0F0;
}
</style>
