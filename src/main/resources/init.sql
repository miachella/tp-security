INSERT INTO public.cell (code, max_prisoners) VALUES
('A1', 4), 
('A2', 2), 
('A3', 4), 
('B1', 3);

INSERT INTO public.crime (jail_time_days, "label") VALUES
(6000, 'Stealing candies'),
(300, 'Murder'),
(600, 'Corruption'),
(3000, 'Rape'),
(100000000, 'Stealing money from the riches');

INSERT INTO public.prisoner (admission_date, danger_level, first_name, last_name, cell_id) VALUES
('2019-06-05', 'HIGH', 'Sylvie', 'MALEZIEUX', 1),
('2019-06-05', 'LOW', 'Isabelle', 'DONNADIEU', 3);

INSERT INTO public.prisoner_crimes (prisoner_id, crimes_id) VALUES
(1, 1),
(2, 2);
