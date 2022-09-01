import {Component, OnInit, ViewChild} from "@angular/core";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {TransportsService} from "../../services/transports.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {RouteCommand} from "../../services/RouteCommand";
import {RouteService} from "../../services/route.service";
import {ItineraryCommand} from "../../services/ItineraryCommand";
import {MatAccordion} from "@angular/material/expansion";
import {LegCommand} from "../../services/LegCommand";
import {ActivatedRoute} from "@angular/router";
import {RouteDto} from "../../model/route.dto";
import {AvailableDaysRentDto} from "../../model/availableDaysRentDto";

@Component({
    selector: 'app-route.edit',
    templateUrl: './route.edit.component.html',
    styleUrls: ['./route.edit.component.css']
})

export class RouteEditComponent implements OnInit {

    @ViewChild(MatAccordion) accordion: MatAccordion;
    legs: LegCommand[] = [];
    routeEditForm !: FormGroup;

    transport: any;
    SelectedValue: any;
    route: RouteDto;
    preselectedDays: string[] = [];
    preselectedTransport: number[] = [];
    // preselectedLegsName: string[];

    ChangeTransport(value) {
        console.log(value);
        this.SelectedValue = value;
    }

    constructor(private formBuilder: FormBuilder,
                private routeService: RouteService,
                private snackBar: MatSnackBar,
                private transportService: TransportsService,
                private router: ActivatedRoute,
    ) {
        this.routeEditForm = formBuilder.group({
            detailedRouteDescription: new FormControl('', [Validators.required]),
            maximalLoadWeight: new FormControl('', [Validators.required]),
            maxLoadVolume: new FormControl('', [Validators.required]),
            availableDaysRentList: new FormControl('', [Validators.required]),
            transportDTOList: new FormControl('', [Validators.required]),
            estimatedAmountTimeShipment: new FormControl('', [Validators.required])

        });
    }

    ngOnInit(): void {
        this.legs.push({name: '', address: '', country: '', countryCode: ''});
        this.legs.push({name: '', address: '', country: '', countryCode: ''});

        this.transportService.getTransports().subscribe((data: any) => {
            this.transport = data;
        });


        console.log("route id = " + this.router.snapshot.params["id"]);
        // console.log(this.legs);

        this.routeService.getRouteById(this.router.snapshot.params["id"])
            .subscribe((result: any) => {
                this.route = result
                this.preselectedDays = this.route.availableDaysRentList.map(d => d.name)
                this.preselectedTransport = this.route.transportDTOList.map(d => d.transportId)
                // this.preselectedLegsName = this.route.itineraryDTO.legDTOS.map(d => d.name)
                // this.preselectedLegsName = this.route.itineraryDTO.legDTOS.map(d => d.name, d.address)
                console.log("getting the route details");
                console.log(result);
                // this.currentRoute = result;
                // this.transportDataSource = new MatTableDataSource<TransportDto>(result.transportDTOList);
                this.routeEditForm = new FormGroup({
                    detailedRouteDescription: new FormControl(result['routeDescription']),
                    maximalLoadWeight: new FormControl(result['maximalLoadWeight']),
                    maxLoadVolume: new FormControl(result['maxLoadVolume']),
                    availableDaysRentList: new FormControl(result['availableDaysRentList']),
                    transportDTOList: new FormControl(result['transportDTOList']),
                    estimatedAmountTimeShipment: new FormControl(result['estimatedAmountTimeShipment'])
                });
            });
    }

    add() {
        this.legs.push({name: '', address: '', country: '', countryCode: ''});
    }

    clearField(legIndex: number) {
        this.legs.splice(legIndex, 1);
    }

    onReset(): void {
        this.routeEditForm.reset();
    }

    updateData() {

        // console.log(this.legs);
        const itinerary: ItineraryCommand = {
            legList: this.legs,
            estimatedAmountTimeShipment: this.routeEditForm.controls['estimatedAmountTimeShipment'].value
        };
        console.log("here");

        const routeCommand: RouteCommand = {
            detailedRouteDescription: this.routeEditForm.controls['detailedRouteDescription'].value,
            maxLoadWeight: this.routeEditForm.controls['maximalLoadWeight'].value,
            maxLoadVolume: this.routeEditForm.controls['maxLoadVolume'].value,
            availableDaysRentList: this.routeEditForm.controls['availableDaysRentList'].value,
            transportIdList: this.routeEditForm.controls['transportDTOList'].value,
            itineraryCommand: itinerary,
        }
        console.log(routeCommand);


        this.routeService.putRoute(routeCommand, this.router.snapshot.params["id"]).subscribe(
            response => {
                console.log("Hurray!");
                this.snackBar.open("Successfully updated", 'OK', {duration: 6000});

            },
            error => {
                console.log(error);
                console.log("Unsuccessful");
                this.snackBar.open("Unsuccessfully updated route", 'OK', {duration: 6000});
            }
        );
    }

    isDaySelected(day: string) : boolean {
        return this.route.availableDaysRentList.some(d => d.name === day)
    }
}




