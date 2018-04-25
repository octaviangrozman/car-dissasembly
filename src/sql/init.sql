CREATE TYPE CPartType AS ENUM('Wheel', 'Engine', 'Windscreen', 'Horn', 'Lights', 'Door',
    						 'Seats','SeatBelts','Steering','Suspension',
    						 'Gearbox','FuelSystem','TrunkTailgate');


create table car
(
	chassisNo int primary key,
	model varchar(20),
	carweight numeric(7,2)
);

create table Pallet
(
	palletNo int primary key,
	weightCapacity numeric(7,2),
	partType CPartType
);

create table Package
(
	packageNo int primary key,
	carModel varchar(20),
	partType CPartType
);

create table Part
(
	ID int primary key,
	carChassisNo int references Car(chassisNo),
	palletNo int references Pallet(palletNo),
	packageNo int references Package(PackageNo),
	partType CPartType,
	carModel varchar(20),
	partWeight numeric(5,2)
);