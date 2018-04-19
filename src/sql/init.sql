create table car
(
	chassisNo int primary key,
	carweight numeric(7,2),
	model varchar(20)
);

create table Pallet
(
	palletNo int primary key,
	weightCapacity numeric(7,2),
	partType varchar(20)
);

create table Package
(
	packageNo int primary key,
	partType varchar(20),
	carModel varchar(20)
);

create table Part
(
	ID int primary key,
	carChassisNo int references Car(chassisNo),
	palletNo int references Pallet(palletNo),
	packageNo int references Package(PackageNo),
	partType varchar(20),
	partWeight numeric(5,2)
);