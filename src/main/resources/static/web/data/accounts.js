const app = Vue.createApp({
    data() {
        return {
            client: {},
            accounts: [],
            accountNumber: 0,
            errorMessage: ""
        }
    },
    
   methods: {
    loadData() {
        axios.get("/api/clients/current")
                .then(resp => {
                    this.client = resp.data;
                    this.accounts = this.sortById(resp.data.accounts);
                    console.log(this.accounts);
                     let animation1 = this.$refs.cubeAnimation;
                     let animation2 = this.$refs.mainDiv;
                     animation1.style.display = "none";
                     animation2.style.display = "block";
                })
    },
    sortById(accountArray){
        accountArray.sort((accountA, accountB) => {
            if(accountA.id < accountB.id){
                return -1
            }
            if(accountA.id > accountB.id){
                return 1
            }
            return 0
        })
        return accountArray
    },
    hasAccounts: function() {
        return this.accounts != 0;
    },
    newAccount() {
        axios.post('/api/clients/current/accounts', {headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => {
                console.log(response);
                location.reload();

            })
            .catch(err => {
                //this.errorMessage = err.response.data
                swal('Error', 'Cannot have more than 3 accounts', 'error');
            })
     },
     deleteAccount() {
        console.log(this.accountNumber)
        axios.put('/api/clients/current/accounts', `accountNumber=${this.accountNumber}`)
            .then(response => {
                console.log(response)
                location.reload();
            })
    },
    signedOut() {
        axios.post('/api/logout')
            .then(response => {
                console.log(response);
                window.location.href = '/web/index.html';
            })
            .catch(err => {
                console.log(err);
            })
    }
   },
   computed: {
   },
   created() {
        setTimeout(() => this.loadData(), 1800 )
    }
})
app.mount("#app");