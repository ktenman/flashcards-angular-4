export class Card {
    constructor(
        public id?: number,
        public front?: string,
        public back?: any,
        public known?: boolean,
        public userId?: number,
    ) {
        this.known = false;
    }
}
