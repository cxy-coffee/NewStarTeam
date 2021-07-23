<template>
	<view class="">
		<view class="box2">
			<view class="view1">-----企业信息-----</view>
			<view class="companyinfoview">
				<text>公司名：</text><br />
				<text class="input1" style="font-size: 25px;font-weight: 500;">{{name}}</text>
			</view>
			<view class="companyinfoview">
				<text>公司ID：</text><br />
				<text class="input1"style="font-size: 25px;font-weight: 500;">{{companyId}}</text>
			</view>
			<view class="companyinfoview">
				<text>法人代表名：</text><br />
				<text class="input1"style="font-size: 25px;font-weight: 500;">{{legalRepresentativeName}}</text>
			</view>
			<view class="companyinfoview">
				<text>电话：</text><br />
				<text class="input1"style="font-size: 25px;font-weight: 500;">{{phone}}</text>
			</view>
			<view class="companyinfoview">
				<text>地址：</text><br/>
				<text class="input1"style="font-size: 25px;font-weight: 500;">{{address}}</text>
			</view>
			<view class="companyinfoview">
				<text>邮箱：</text>
				<text class="input1"style="font-size: 25px;font-weight: 500;">{{email}}</text>
			</view>


		</view>
		<view class="department">

			<text style="font-size: 25px;">部门：</text><br />
			<text style="font-size: 20px;">部门名&#12288部门ID</text>
			<view class="" v-for="(arritem,index) in departments">
				{{arritem.name}}&#12288&#12288{{arritem.departmentId}}
				<button type="warn" style="float: right;" @click="remove(arritem,index)">删除</button>
				<button type="primary" class="changebutton" @click="ensureChangeCompanyInfo(arritem)">修改</button>
			</view>
		</view>


	</view>
</template>

<script>
	export default {
		data() {
			return {
				companyId: undefined,
				name: undefined,
				legalRepresentativeName: undefined,
				email: undefined,
				address: undefined,
				phone: undefined,
				experiences: undefined,
				departments: [],
			};
		},
		methods: {
			ensureChangeCompanyInfo(arritem) {
				var item = JSON.stringify(arritem);
				uni.navigateTo({
					url: "../changeCompanyInfo/changeCompanyInfo?item=" + item,
				})
			},
			remove(arritem,index){
				uni.showModal({
					title: '提示',
					content: '您确定要删除您公司该部门信息吗',
					success: (res)=>{
						if (res.confirm) {
							
							uni.request({
								url: 'http://123.57.94.22:9091/deleteDepartment.do',
								data: {
									companyId : this.companyId ,
									departmentId : arritem.departmentId ,
									
								},
								header: {
									'content-type': 'application/x-www-form-urlencoded',
									'Accept': "application/json;charset=UTF-8"
								},
								method: 'POST',
								success: res => {
									if(res.data){
										uni.showToast({
											title:'删除成功！',
											icon:'none',
											duration: 2000,
										}),
										console.log(res);
										this.departments.splice(index,1)
									}
									else
									{
										uni.showToast({
											title:'异常！请联系管理员！',
											icon:'none',
											duration: 2000,
										})
									}
				
								}
							})
				
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				})
				
			}


		}, //method
		onLoad: function(UserInfo) {
			var that = this
			that.companyId=uni.getStorageSync("companyId");



			uni.request({
				url: 'http://123.57.94.22:9091/getCompanyByCompanyId.do',
				data: {
					companyId: this.companyId,
				},
				header: {
					'content-type': 'application/x-www-form-urlencoded',
					'Accept': "application/json;charset=UTF-8"
				},
				method: 'POST',
				success: res => {
					this.name = res.data.name;
					this.legalRepresentativeName = res.data.legalRepresentativeName;
					this.email = res.data.email;
					this.address = res.data.address;
					this.phone = res.data.phone;
					for(var i=0 ; i<=res.data.departments.length-1;i++){
						this.departments.push(res.data.departments[i])
						
					}
					uni.setNavigationBarTitle({
						title: this.name + "信息",
					})
				},
			})


			


		}

	}
</script>

<style>
	.companyinfoview {

		margin-left: 30px;
		margin-right: 30px;
		font-size: 20px;
		font-weight: 800;
	}

	.department {
		margin-left: 30px;
		margin-right: 30px;
		margin-top: 10px;
		line-height: 50px;
		border-radius: 25px;
		background: linear-gradient(180deg, #aaffff 0%, #fff 45%, #fff 55%, #aaffff 100%);
		font-size: 20px;
		font-weight: 800;
	}

	,

	.box2 {
		margin-left: 20px;
		margin-right: 20px;
		margin-top: 70px;
		line-height: 50px;
		border-radius: 25px;
		background: linear-gradient(180deg, #aaffff 0%, #fff 45%, #fff 55%, #aaffff 100%);
	}

	,

	.view1 {
		text-align: center;
		font-size: 28px;
	}

	,



	.input1 {
		min-height: 15px;
		height: 15px;
		font-size: 15px;
		border: 15px;
	}

	,


	.changebutton {
		align-items: center;
		border-radius: 10px;
		float: right;



	}
</style>
