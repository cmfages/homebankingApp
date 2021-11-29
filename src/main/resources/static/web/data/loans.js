const app = Vue.createApp({
    data() {
        return {
            client: {},
            loans: [],
            clientLoans: [],
            accounts: [],
            toAccNumber: "",
            loanType: "",
            inputAmount: 0,
            paymentsSelected: 0,
        }
    },
    
   methods: {
    loadData() {
        axios.get("/api/clients/current")
                .then(resp => {
                    this.client = resp.data;
                    this.clientLoans = this.sortById(resp.data.clientLoans);
                    this.accounts = this.sortById(resp.data.accounts);
                    console.log(this.client);
                    console.log(this.clientLoans);
                    console.log(this.accounts);
                    let animation1 = this.$refs.cubeAnimation;
                    let animation2 = this.$refs.mainDiv;
                    animation1.style.display = "none";
                    animation2.style.display = "block";
                })

    },
    loadLoans() {
        axios.get("/api/loans")
            .then(response => {
                this.loans = [...response.data];
                console.log(response.data);
            })
            .catch(err =>
                console.log(err.response));
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
    hasLoans: function() {
        return this.clientLoans != 0;
    },
    newLoan() {
        console.log(this.toAccNumber);
        console.log(this.loanType);
        console.log(this.inputAmount);
        console.log(this.paymentsSelected);
        axios.post('/api/loans', {loanId: this.loanType, amountRequest: this.inputAmount, payments: this.paymentsSelected, destinationAccountNumber: this.toAccNumber})
            .then(response => {
                console.log(response);
                swal('Approved', 'New loan adquired')
                location.reload();
            })
            .catch(err => {
                swal('Error', err.response.data, 'error');
                console.log(err.response.data);
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
        setTimeout(() => this.loadLoans(), 1800) 
    }
})
let asd = app.mount("#app");