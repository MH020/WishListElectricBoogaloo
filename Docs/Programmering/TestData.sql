INSERT INTO `localwishdb`.`Market` (`market_id`, `city`) VALUES 
(1, 'København'),
(2, 'Aarhus'),
(3, 'Odense'),
(4, 'Aalborg'),
(5, 'Esbjerg');

INSERT INTO `localwishdb`.`Product` (`product_name`, `product_description`, `product_price`, `market_id`) VALUES 
-- Market 1 (København)
('Martins Hjemmestrikket Uldtæppe', 'Blødt, håndlavet uldtæppe til kolde aftener', 450.00, 1),
('Lises Keramiske Skål', 'Unik, håndlavet skål i keramik', 120.00, 1),
('Henriks Lysestage af Genbrugstræ', 'Rustik lysestage lavet af genbrugt træ', 80.00, 1),

-- Market 2 (Aarhus)
('Annes Håndlavede Læderpung', 'Klassisk læderpung, håndsyet med omhu', 250.00, 2),
('Peters Filtede Hjemmesko', 'Varme hjemmesko lavet af filtet uld', 150.00, 2),
('Karens Strikkede Uldsokker', 'Tykkere, hjemmestrikkede uldsokker til vinteren', 75.00, 2),

-- Market 3 (Odense)
('Jespers Hjemmelavede Honning', 'Ren honning fra lokal biavler', 60.00, 3),
('Maries Dansk Sylteglas', 'Håndlavet glas fyldt med syltede rødbeder', 45.00, 3),
('Hans Dekorative Træskærearbejder', 'Håndskårne træfigurer inspireret af dansk natur', 180.00, 3),

-- Market 4 (Aalborg)
('Ellens Broderede Pudebetræk', 'Håndbroderet pudebetræk med blomsterdesign', 130.00, 4),
('Anders’ Keramiske Krus', 'Håndlavet krus i dansk keramiktradition', 95.00, 4),
('Lottes Flettede Kurv', 'Håndflettet kurv til opbevaring', 150.00, 4),

-- Market 5 (Esbjerg)
('Martins Håndlavet Sæbe', 'Naturlig sæbe lavet med danske urter', 40.00, 5),
('Niels’ Trælegetøj', 'Klassisk håndlavet trælegetøj til børn', 85.00, 5),
('Sofies Strikkede Halstørklæde', 'Lækkert halstørklæde strikket i blød uld', 200.00, 5);