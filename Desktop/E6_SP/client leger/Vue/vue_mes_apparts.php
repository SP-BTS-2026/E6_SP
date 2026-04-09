<link rel="stylesheet" href="Style/style_mes_apparts.css">

<div class="container mes-apparts-container">

    <div class="page-header">
        <div>
            <h1><i class="fas fa-building"></i> Mes Appartements</h1>
            <p class="page-sub">Gérez vos logements mis en location</p>
        </div>
        <a href="index.php?page=ajout_appart" class="btn-add">
            <i class="fas fa-plus"></i> Ajouter un appartement
        </a>
    </div>

    <?php if(isset($_GET['success'])): ?>
        <div class="alert-success">
            <i class="fas fa-check-circle"></i>
            <?php
                if($_GET['success'] == 'ajout')    echo "Appartement ajouté avec succès !";
                if($_GET['success'] == 'modif')    echo "Appartement modifié avec succès !";
                if($_GET['success'] == 'supprime') echo "Appartement supprimé avec succès !";
            ?>
        </div>
    <?php endif; ?>

    <?php if(empty($mesApparts)): ?>
        <div class="empty-state">
            <i class="fas fa-home"></i>
            <p>Vous n'avez pas encore d'appartement.</p>
            <a href="index.php?page=ajout_appart" class="btn-add">
                <i class="fas fa-plus"></i> Ajouter mon premier appartement
            </a>
        </div>
    <?php else: ?>
        <div class="apparts-grid">
            <?php foreach($mesApparts as $appart): ?>
                <article class="appart-card-admin">
                    <div class="card-image">
                        <img src="images/chalets/<?= htmlspecialchars($appart['image']) ?>"
                             alt="<?= htmlspecialchars($appart['type_appart']) ?>"
                             onerror="this.src='images/background-montagne.jpg'">
                        <span class="card-price">
                            <?= number_format($appart['prix_hebdo'] / 7, 0, ',', ' ') ?> € / nuit
                        </span>
                    </div>
                    <div class="card-body">
                        <h3><?= htmlspecialchars($appart['type_appart']) ?> — <?= htmlspecialchars($appart['num_appart']) ?></h3>
                        <div class="card-specs">
                            <span><i class="fas fa-expand"></i> <?= $appart['surface'] ?> m²</span>
                            <span><i class="fas fa-users"></i> <?= $appart['capacite_accueil'] ?> pers.</span>
                            <span><i class="fas fa-skiing"></i> <?= $appart['distance_pistes'] ?? '?' ?> m</span>
                        </div>
                        <div class="card-price-hebdo">
                            <?= number_format($appart['prix_hebdo'], 2, ',', ' ') ?> € / semaine
                        </div>
                    </div>
                    <div class="card-actions">
                        <a href="index.php?page=modif_appart&id=<?= $appart['id_appart'] ?>" class="btn-edit">
                            <i class="fas fa-pen"></i> Modifier
                        </a>
                        <a href="index.php?page=suppr_appart&id=<?= $appart['id_appart'] ?>"
                           class="btn-delete"
                           onclick="return confirm('Supprimer cet appartement ? Cette action est irréversible.')">
                            <i class="fas fa-trash"></i> Supprimer
                        </a>
                    </div>
                </article>
            <?php endforeach; ?>
        </div>
    <?php endif; ?>
</div>
