import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiLanguageService, DataUtils } from 'ng-jhipster';
import { Card } from './card.model';
import { CardService } from './card.service';

@Component({
    selector: 'jhi-card-detail',
    templateUrl: './card-detail.component.html'
})
export class CardDetailComponent implements OnInit, OnDestroy {

    card: Card;
    private subscription: any;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private dataUtils: DataUtils,
        private cardService: CardService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['card']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe(params => {
            this.load(params['id']);
        });
    }

    load (id) {
        this.cardService.find(id).subscribe(card => {
            this.card = card;
        });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }

}
