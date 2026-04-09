<link rel="stylesheet" href="Style/style_mes_apparts.css">

<div class="container form-appart-container">

    <a href="index.php?page=mes_apparts" class="btn-back">
        <i class="fas fa-arrow-left"></i> Retour à mes appartements
    </a>

    <div class="form-card">
        <h1><i class="fas fa-plus-circle"></i> Ajouter un appartement</h1>

        <?php if(isset($erreur)): ?>
            <div class="alert-error"><i class="fas fa-exclamation-circle"></i> <?= $erreur ?></div>
        <?php endif; ?>

        <form method="POST" action="index.php?page=ajout_appart">
            <div class="form-grid">
                <div class="form-group">
                    <label>Numéro d'appartement</label>
                    <input type="text" name="num_appart" required placeholder="Ex: A12">
                </div>
                <div class="form-group">
                    <label>Type</label>
                    <select name="type_appart" required>
                        <option value="">-- Choisir --</option>
                        <option value="Studio">Studio</option>
                        <option value="T2">T2</option>
                        <option value="T3">T3</option>
                        <option value="T4">T4</option>
                        <option value="Chalet">Chalet</option>
                        <option value="Duplex">Duplex</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Surface (m²)</label>
                    <input type="number" name="surface" step="0.01" required placeholder="Ex: 45.00">
                </div>
                <div class="form-group">
                    <label>Capacité d'accueil</label>
                    <input type="number" name="capacite_accueil" required placeholder="Ex: 4">
                </div>
                <div class="form-group">
                    <label>Exposition</label>
                    <select name="exposition">
                        <option value="">-- Choisir --</option>
                        <option value="Nord">Nord</option>
                        <option value="Sud">Sud</option>
                        <option value="Est">Est</option>
                        <option value="Ouest">Ouest</option>
                        <option value="Sud-Est">Sud-Est</option>
                        <option value="Sud-Ouest">Sud-Ouest</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Distance pistes (m)</label>
                    <input type="number" name="distance_pistes" placeholder="Ex: 200">
                </div>
                <div class="form-group">
                    <label>Prix hebdomadaire (€)</label>
                    <input type="number" name="prix_hebdo" step="0.01" required placeholder="Ex: 650.00">
                </div>
                <div class="form-group">
                    <label>Nom de l'image</label>
                    <input type="text" name="image" placeholder="Ex: appart12.jpg" value="default.jpg">
                </div>
            </div>

            <button type="submit" name="btnAjout" class="btn-submit">
                <i class="fas fa-save"></i> Enregistrer l'appartement
            </button>
        </form>
    </div>
</div>
