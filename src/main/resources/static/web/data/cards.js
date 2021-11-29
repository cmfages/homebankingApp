const app = Vue.createApp({
    data() {
        return {
            client: {},
            cards: [],
            type: "",
            color: "",
            cardsLength: 0,
            cardId: 0
        }
    },
    
   methods: {
    loadData() {
        axios.get("/api/clients/current")
                .then(resp => {
                    this.client = resp.data;
                    this.cards = this.sortById(resp.data.cards);
                    this.cardsLength = this.cards.length;
                    console.log(this.client);
                    console.log(this.cards);
                    console.log(this.cards.length);
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
    isDebit: function() {
        return this.cards.type = "DEBIT";
    },
    isCredit: function() {
        return this.cards.type = "CREDIT";
    },
    hasCards: function() {
        return this.cards != 0;
    },
    newCard() {
        console.log(this.type)
        console.log(this.color)
        axios.post('/api/clients/current/cards', `cardType=${this.type}&cardColor=${this.color}`,
        {headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => {
                console.log(response);
                swal('Approved', 'New card created')
                setTimeout(() => location.reload(), 2500 );
            })
            .catch(err => {
                console.log(err);
                swal('Error', 'Cannot have more than 3 cards of the same type', 'error');
            })
    },
    deleteCard() {
        console.log(this.cardId)
        axios.put('/api/clients/current/cards', `cardId=${this.cardId}`)
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
let asd = app.mount("#app");