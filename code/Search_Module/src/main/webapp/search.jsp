<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Tìm kiếm sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        .search-container { position: relative; }
        .suggestion-box {
            position: absolute; top: 100%; left: 0; right: 0;
            background: white; border: 1px solid #ced4da; border-top: none;
            z-index: 1000; display: none; box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
        .suggestion-item { padding: 10px; cursor: pointer; border-bottom: 1px solid #eee; }
        .suggestion-item:hover { background-color: #f8f9fa; color: #0d6efd; }
        .suggestion-item:last-child { border-bottom: none; }
        
        .product-card { transition: transform 0.2s; height: 100%; }
        .product-card:hover { transform: translateY(-5px); box-shadow: 0 4px 15px rgba(0,0,0,0.1); }
        .product-img { height: 200px; object-fit: cover; background-color: #eee; }
    </style>
</head>
<body class="bg-light">

<div class="container py-5">
    <h1 class="text-center mb-4 text-primary fw-bold">Shop Giày/Dép</h1>

    <div class="card shadow-sm p-4 mb-5 bg-white rounded">
        <form action="search" method="get">
            <div class="row g-3">
                <div class="col-md-4 search-container">
                    <label class="form-label fw-bold">Từ khóa</label>
                    <input type="text" class="form-control" name="keyword" 
                           id="searchInput" placeholder="Tìm tên giày..." 
                           value="${key}" autocomplete="off">
                    <div id="suggestionBox" class="suggestion-box rounded-bottom"></div>
                </div>

                <div class="col-md-3">
                    <label class="form-label fw-bold">Khoảng giá (VNĐ)</label>
                    <div class="input-group">
                        <input type="number" class="form-control" name="minPrice" 
                               placeholder="Từ" value="${minPrice}" min="0">
                        <span class="input-group-text">-</span>
                        <input type="number" class="form-control" name="maxPrice" 
                               placeholder="Đến" value="${maxPrice}" min="0">
                    </div>
                </div>

                <div class="col-md-2">
                    <label class="form-label fw-bold">Size</label>
                    <select name="size" class="form-select">
                        <option value="">Tất cả</option>
                        <option value="FreeSize" ${size == 'FreeSize' ? 'selected' : ''}>FreeSize</option>
                        <option value="35" ${size == '35' ? 'selected' : ''}>35</option>
                        <option value="36" ${size == '36' ? 'selected' : ''}>36</option>
                        <option value="37" ${size == '37' ? 'selected' : ''}>37</option>
                        <option value="38" ${size == '38' ? 'selected' : ''}>38</option>
                        <option value="39" ${size == '39' ? 'selected' : ''}>39</option>
                        <option value="40" ${size == '40' ? 'selected' : ''}>40</option>
                        <option value="41" ${size == '41' ? 'selected' : ''}>41</option>
                        <option value="42" ${size == '42' ? 'selected' : ''}>42</option>
                    </select>
                </div>

                <div class="col-md-2">
                    <label class="form-label fw-bold">Màu sắc</label>
                    <select name="color" class="form-select">
                        <option value="">Tất cả</option>
                        <option value="Trắng" ${color == 'Trắng' ? 'selected' : ''}>Trắng</option>
                        <option value="Đen" ${color == 'Đen' ? 'selected' : ''}>Đen</option>
                        <option value="Xanh" ${color == 'Xanh' ? 'selected' : ''}>Xanh</option>
                        <option value="Đỏ" ${color == 'Đỏ' ? 'selected' : ''}>Đỏ</option>
                        <option value="Vàng" ${color == 'Vàng' ? 'selected' : ''}>Vàng</option>
                        <option value="Nâu" ${color == 'Nâu' ? 'selected' : ''}>Nâu</option>
                        <option value="Xám" ${color == 'Xám' ? 'selected' : ''}>Xám</option>
                        <option value="Hồng" ${color == 'Hồng' ? 'selected' : ''}>Hồng</option>
                        <option value="Be" ${color == 'Be' ? 'selected' : ''}>Be</option>
                    </select>
                </div>

                <div class="col-md-1 d-flex align-items-end">
                    <button type="submit" class="btn btn-primary w-100 fw-bold">
                        <i class="bi bi-search"></i>
                    </button>
                </div>
            </div>
        </form>
    </div>

    <c:choose>
        
        <c:when test="${not empty results}">
            
            <c:if test="${not empty key || not empty size || not empty color || not empty minPrice || not empty maxPrice}">
                <h4 class="mb-3">
                    Kết quả tìm kiếm: <span class="text-danger">${results.size()}</span> sản phẩm phù hợp
                </h4>
            </c:if>
            <c:if test="${empty key && empty size && empty color && empty minPrice && empty maxPrice}">
                <h4 class="mb-3 text-primary fw-bold border-bottom pb-2">
                    Tất cả sản phẩm mới nhất
                </h4>
            </c:if>
            
            <div class="row row-cols-1 row-cols-md-4 g-4">
                <c:forEach var="p" items="${results}">
                    <div class="col">
                        <div class="card product-card h-100 border-0 shadow-sm">
                            <img src="${p.imageUrl}" class="card-img-top product-img" alt="${p.name}">
                            <div class="card-body d-flex flex-column">
                                <h6 class="card-title fw-bold text-truncate">${p.name}</h6>
                                <p class="card-text text-danger fs-5 fw-bold mt-auto">
                                    <fmt:formatNumber value="${p.price}" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                                </p>
                                <a href="detail?id=${p.id}" class="btn btn-sm btn-outline-primary w-100">Xem chi tiết</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>

        <c:otherwise>
            <div class="text-center py-4">
                
                <div class="mb-5">
                    <h4 class="text-secondary fw-bold">
                        Kết quả tìm kiếm: <span class="text-danger">0</span> sản phẩm phù hợp
                    </h4>
                    <p class="text-muted">Rất tiếc, chúng tôi không tìm thấy sản phẩm nào khớp với từ khóa của bạn.</p>
                </div>

                <h5 class="text-primary fw-bold text-uppercase mb-4 pt-3 border-top">
                    <i class="bi bi-stars text-warning"></i> CÓ THỂ BẠN SẼ THÍCH
                </h5>
                
                <div class="row row-cols-2 row-cols-md-4 g-4 justify-content-center">
                    <c:forEach var="s" items="${suggestedProducts}">
                        <div class="col">
                            <div class="card h-100 border-0 shadow-sm product-card">
                                <div class="position-absolute top-0 start-0 p-2 z-2">
                                    <span class="badge bg-success shadow-sm" style="font-size: 0.7rem;">Gợi ý</span>
                                </div>

                                <div class="overflow-hidden bg-white rounded-top" style="height: 180px;">
                                    <img src="${s.imageUrl}" class="card-img-top w-100 h-100" 
                                         style="object-fit: cover;" alt="${s.name}">
                                </div>

                                <div class="card-body d-flex flex-column text-center p-2">
                                    <h6 class="card-title fw-bold text-dark text-truncate mb-1" style="font-size: 0.9rem;" title="${s.name}">
                                        ${s.name}
                                    </h6>
                                    
                                    <p class="card-text text-danger fw-bold mb-2" style="font-size: 0.95rem;">
                                        <fmt:formatNumber value="${s.price}" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                                    </p>
                                    
                                    <div class="mt-auto">
                                        <button type="button" class="btn btn-outline-primary btn-sm w-100 rounded-pill fw-bold text-nowrap">
                                            Xem ngay
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
    
</div>

<script>
    const input = document.getElementById('searchInput');
    const box = document.getElementById('suggestionBox');

    input.addEventListener('input', function() {
        const query = this.value.trim();
        if (query.length < 1) { 
            box.style.display = 'none';
            return;
        }

        fetch('suggestion?q=' + encodeURIComponent(query))
            .then(response => {
                if (!response.ok) throw new Error('Lỗi mạng');
                return response.json();
            })
            .then(data => {
                box.innerHTML = '';
                if (data.length > 0) {
                    data.forEach(text => {
                        const item = document.createElement('div');
                        item.className = 'suggestion-item';
                        item.textContent = text; 
                        item.onclick = function() { selectItem(text); };
                        box.appendChild(item);
                    });
                    box.style.display = 'block';
                } else {
                    box.style.display = 'none';
                }
            })
            .catch(err => {
                console.error("Lỗi JS:", err);
                box.style.display = 'none';
            });
    });

    function selectItem(text) {
        input.value = text;
        box.style.display = 'none';
    }

    document.addEventListener('click', function(e) {
        if (!input.contains(e.target) && !box.contains(e.target)) {
            box.style.display = 'none';
        }
    });
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>